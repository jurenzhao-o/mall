package com.jurenzhao.mall.controller;

import com.github.pagehelper.PageInfo;
import com.jurenzhao.mall.common.ApiRestResponse;
import com.jurenzhao.mall.common.Constant;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.exception.MallExceptionEnum;
import com.jurenzhao.mall.model.request.AddProductReq;
import com.jurenzhao.mall.model.request.UpdateProductReq;
import com.jurenzhao.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * @autor :JuRenZhao
 * @Date :Created in 20:26 2022/5/9
 * @Description :后台商品管理controller
 */
@RestController
public class ProductAdminController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) throws MallException {
        productService.addProduct(addProductReq);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/upload/file")
    public ApiRestResponse uploadPic(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws MallException {
        String originalFilename = file.getOriginalFilename();
        String suffixName = originalFilename.substring(originalFilename.indexOf("."));
        String newFileName = UUID.randomUUID().toString()+suffixName;
        File dir = new File(Constant.FILE_UPLOAD_DIR);
        File outfile = new File(Constant.FILE_UPLOAD_DIR+newFileName);
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            file.transferTo(outfile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MallException(MallExceptionEnum.UPLOAD_FILE_FAIL);
        }
        try {
            return ApiRestResponse.success(getHost(new URI(request.getRequestURL() + ""))+"/images/"+newFileName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ApiRestResponse.error(MallExceptionEnum.UPLOAD_FILE_FAIL);
        }

    }

    private URI getHost(URI uri){
        URI effectiveURI = null;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return effectiveURI;
    }

    @PostMapping("/admin/product/update")
    public ApiRestResponse updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq) throws MallException {
        productService.updateProduct(updateProductReq);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/product/delete")
    public ApiRestResponse deleteUpdate(@RequestParam Integer id) throws MallException {
        productService.deleteProductById(id);
        return ApiRestResponse.success();
    }
    @PostMapping("/admin/product/batchUpdateSellStatus")
    public ApiRestResponse batchUpdateSellStatus(@RequestParam Integer [] ids,@RequestParam Integer sellStatus){
        productService.batchUpdateSellStatus(ids,sellStatus);
        return ApiRestResponse.success();
    }

    @GetMapping("/admin/product/list")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo pageInfo = productService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }


}
