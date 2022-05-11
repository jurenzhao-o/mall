package com.jurenzhao.mall.service;

import com.github.pagehelper.PageInfo;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.model.pojo.Product;
import com.jurenzhao.mall.model.request.AddProductReq;
import com.jurenzhao.mall.model.request.ProductListReq;
import com.jurenzhao.mall.model.request.UpdateProductReq;

/**
 * @autor :JuRenZhao
 * @Date :Created in 20:38 2022/5/9
 * @Description :商品Service
 */
public interface ProductService {

    void addProduct(AddProductReq addProductReq) throws MallException;

    void updateProduct(UpdateProductReq updateProductReq) throws MallException;

    void deleteProductById(Integer id) throws MallException;

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    PageInfo listForCustomer(ProductListReq productListReq);

    Product getDetailById(Integer id) throws MallException;
}
