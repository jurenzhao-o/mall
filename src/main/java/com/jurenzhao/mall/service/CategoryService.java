package com.jurenzhao.mall.service;

import com.github.pagehelper.PageInfo;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.model.pojo.Category;
import com.jurenzhao.mall.model.request.AddCategoryReq;
import com.jurenzhao.mall.model.request.UpdateCategoryReq;
import com.jurenzhao.mall.model.vo.CategoryVO;

import java.util.List;

/**
 * @autor :JuRenZhao
 * @Date :Created in 20:54 2022/5/6
 * @Description :商品分类service
 */
public interface CategoryService {

    public void addCategory(AddCategoryReq addCategoryReq) throws MallException;

    void updateCategory(UpdateCategoryReq updateCategoryReq) throws MallException;

    void deleteById(Integer id) throws MallException;

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> listForCustomer(Integer parentId);
}
