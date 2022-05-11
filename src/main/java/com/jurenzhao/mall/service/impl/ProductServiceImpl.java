package com.jurenzhao.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jurenzhao.mall.common.Constant;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.exception.MallExceptionEnum;
import com.jurenzhao.mall.model.dao.ProductMapper;
import com.jurenzhao.mall.model.pojo.Product;
import com.jurenzhao.mall.model.query.ProductListQuery;
import com.jurenzhao.mall.model.request.AddProductReq;
import com.jurenzhao.mall.model.request.ProductListReq;
import com.jurenzhao.mall.model.request.UpdateProductReq;
import com.jurenzhao.mall.model.vo.CategoryVO;
import com.jurenzhao.mall.service.CategoryService;
import com.jurenzhao.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor :JuRenZhao
 * @Date :Created in 20:38 2022/5/9
 * @Description :商品实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;

    @Override
    public void addProduct(AddProductReq addProductReq) throws MallException {
        Product productOld = productMapper.selectByName(addProductReq.getName());
        if(productOld != null){
            throw new MallException(MallExceptionEnum.EXIT_PRODUCT);
        }
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq,product);
        int count = productMapper.insertSelective(product);
        if(count == 0){
            throw new MallException(MallExceptionEnum.CREATE_FAIL);
        }
    }

    @Override
    public void updateProduct(UpdateProductReq updateProductReq) throws MallException {
        Product productOld = productMapper.selectByName(updateProductReq.getName());
        if(productOld != null && !productOld.getId().equals(updateProductReq.getId())){
            throw new MallException(MallExceptionEnum.EXIT_PRODUCT);
        }
        Product product = new Product();
        BeanUtils.copyProperties(updateProductReq,product);
        int count = productMapper.updateByPrimaryKeySelective(product);
        if(count == 0){
            throw new MallException(MallExceptionEnum.UPDATE_FAIL);
        }
    }


    @Override
    public void deleteProductById(Integer id) throws MallException {
        Product product = productMapper.selectByPrimaryKey(id);
        if(product == null){
            throw new MallException(MallExceptionEnum.NOEXIT_PRODUCT);
        }
        int count = productMapper.deleteByPrimaryKey(id);
        if(count == 0){
            throw new MallException(MallExceptionEnum.DELETE_FAIL);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus){
        productMapper.batchUpdateSellStatus(ids,sellStatus);
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productMapper.listForAdmin();
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    }

    @Override
    public PageInfo listForCustomer(ProductListReq productListReq){
        //构建query对象
        ProductListQuery productListQuery = new ProductListQuery();

        if(!StringUtils.isEmpty(productListReq.getKeyword())){
            StringBuilder append = new StringBuilder().append("%").append(productListReq.getKeyword()).append("%");
            productListQuery.setKeyword(append.toString());
        }

        if(productListReq.getCategoryId() != null){
            List<CategoryVO> categoryVOList = categoryService.listForCustomer(productListReq.getCategoryId());
            List<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(productListReq.getCategoryId());
            getCategoryIds(categoryVOList,categoryIds);
            productListQuery.setCategorys(categoryIds);
        }
        //排序处理
        String orderBy = productListReq.getOrderBy();
        if(Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
            PageHelper.startPage(productListReq.getPageNum(),productListReq.getPageSize(),orderBy);
        }
        else {
            PageHelper.startPage(productListReq.getPageNum(),productListReq.getPageSize());
        }
        List<Product> products = productMapper.selectList(productListQuery);
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;

    }

    private void getCategoryIds(List<CategoryVO> categoryVOList,List<Integer> categoryIds){
        for (int i = 0; i < categoryVOList.size(); i++) {
            CategoryVO categoryVO = categoryVOList.get(i);
            if(categoryVO != null){
                categoryIds.add(categoryVO.getId());
                getCategoryIds(categoryVO.getChildCategory(),categoryIds);
            }
        }
    }

    @Override
    public Product getDetailById(Integer id) throws MallException {
        Product product = productMapper.selectByPrimaryKey(id);
        if(product == null){
            throw new MallException(MallExceptionEnum.NOEXIT_PRODUCT);
        }
        return product;
    }


}
