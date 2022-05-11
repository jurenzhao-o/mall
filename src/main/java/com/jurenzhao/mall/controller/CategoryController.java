package com.jurenzhao.mall.controller;

import com.github.pagehelper.PageInfo;
import com.jurenzhao.mall.common.ApiRestResponse;
import com.jurenzhao.mall.common.Constant;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.exception.MallExceptionEnum;
import com.jurenzhao.mall.model.pojo.User;
import com.jurenzhao.mall.model.request.AddCategoryReq;
import com.jurenzhao.mall.model.request.UpdateCategoryReq;
import com.jurenzhao.mall.model.vo.CategoryVO;
import com.jurenzhao.mall.service.CategoryService;
import com.jurenzhao.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @autor :JuRenZhao
 * @Date :Created in 18:56 2022/5/6
 * @Description :商品分类控制器
 */
@Controller
public class CategoryController {

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @ApiOperation("添加商品目录")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory( @Valid @RequestBody AddCategoryReq addCategoryReq, HttpSession session) throws MallException {
        User user = (User) session.getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }

        if(userService.checkAdminRole(user)){
            categoryService.addCategory(addCategoryReq);
            return ApiRestResponse.success();
        }
        else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }
    @ApiOperation("更新商品目录")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq,HttpSession session) throws MallException {
        User user = (User) session.getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null){
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        if(userService.checkAdminRole(user)){
            categoryService.updateCategory(updateCategoryReq);
            return ApiRestResponse.success();
        }
        else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }
    }
    @ApiOperation("删除商品目录")
    @PostMapping("/admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id) throws MallException {
        categoryService.deleteById(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("分类列表")
    @GetMapping("/admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("分类列表（递归）")
    @GetMapping("category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForCustomer(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        List<CategoryVO> categoryVOS = categoryService.listForCustomer(0);
        return ApiRestResponse.success(categoryVOS);
    }

}
