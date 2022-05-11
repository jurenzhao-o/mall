package com.jurenzhao.mall.model.dao;

import com.jurenzhao.mall.model.pojo.Product;
import com.jurenzhao.mall.model.query.ProductListQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByName(String name);

    void batchUpdateSellStatus(@Param("ids") Integer [] ids, @Param("sellStatus") Integer sellStauts);


    List<Product> listForAdmin();

    List<Product> selectList(@Param("query") ProductListQuery query);
}