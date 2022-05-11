package com.jurenzhao.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jurenzhao.mall.exception.MallException;
import com.jurenzhao.mall.exception.MallExceptionEnum;
import com.jurenzhao.mall.model.dao.CategoryMapper;
import com.jurenzhao.mall.model.pojo.Category;
import com.jurenzhao.mall.model.request.AddCategoryReq;
import com.jurenzhao.mall.model.request.UpdateCategoryReq;
import com.jurenzhao.mall.model.vo.CategoryVO;
import com.jurenzhao.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @autor :JuRenZhao
 * @Date :Created in 20:55 2022/5/6
 * @Description :商品分类实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void addCategory(AddCategoryReq addCategoryReq) throws MallException {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq,category);
        String name = category.getName();
        Category categoryOld = categoryMapper.selectByName(name);
        if(categoryOld != null){
            throw new MallException(MallExceptionEnum.NAME_EXITS);
        }
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        int count = categoryMapper.insert(category);
        if(count == 0){
            throw new MallException(MallExceptionEnum.CREATE_FAIL);
        }
    }

    @Override
    public void updateCategory(UpdateCategoryReq updateCategoryReq) throws MallException {
        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq,category);
        Category categoryOld = categoryMapper.selectByPrimaryKey(category.getId());
        if(categoryOld == null){
            throw new MallException(MallExceptionEnum.CATEGORY_NOTFIND);
        }
        else {
            if(!categoryOld.getName().equals(updateCategoryReq.getName())){
                throw new MallException(MallExceptionEnum.NAME_EXITS);
            }
        }
        int count = categoryMapper.updateByPrimaryKey(category);
        if(count == 0){
            throw new MallException(MallExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void deleteById(Integer id) throws MallException {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if(category == null){
            throw new MallException(MallExceptionEnum.CATEGORY_NOTFIND);
        }
        else {
            int count = categoryMapper.deleteByPrimaryKey(id);
            if(count == 0){
                throw new MallException(MallExceptionEnum.DELETE_FAIL);
            }
        }
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,"type,order_num");
        List<Category> categoriesList = categoryMapper.selectAll();
        PageInfo pageInfo = new PageInfo(categoriesList);
        return pageInfo;
    }

    @Override
    //@Cacheable(value = "listCategoryForCustomer")
    public List<CategoryVO> listForCustomer(Integer parentId){
        List<CategoryVO> categoryVOList = new ArrayList<>();
        recursionCategory(categoryVOList,parentId);
        return categoryVOList;
    }

    private void recursionCategory(List<CategoryVO> list,Integer parentId){
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);
        if(!CollectionUtils.isEmpty(categoryList)){
            for (Category category : categoryList) {
                CategoryVO categoryVO = new CategoryVO();
                BeanUtils.copyProperties(category,categoryVO);
                list.add(categoryVO);
                recursionCategory(categoryVO.getChildCategory(),categoryVO.getId());
            }
        }

    }
}
