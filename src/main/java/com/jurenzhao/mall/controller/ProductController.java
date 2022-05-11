package com.jurenzhao.mall.controller;

import com.github.pagehelper.PageInfo;
import com.jurenzhao.mall.common.ApiRestResponse;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.model.pojo.Product;
import com.jurenzhao.mall.model.request.ProductListReq;
import com.jurenzhao.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autor :JuRenZhao
 * @Date :Created in 16:31 2022/5/10
 * @Description :商品（前台）控制器
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/list")
    public ApiRestResponse listForCustomer(ProductListReq productListReq){
        PageInfo pageInfo = productService.listForCustomer(productListReq);
        return ApiRestResponse.success(pageInfo);
    }
    @GetMapping("/product/detail")
    public ApiRestResponse getDetail(@RequestParam Integer id) throws MallException {
        Product product = productService.getDetailById(id);
        return ApiRestResponse.success(product);
    }
}
