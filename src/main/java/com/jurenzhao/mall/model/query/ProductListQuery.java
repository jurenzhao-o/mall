package com.jurenzhao.mall.model.query;

import java.util.List;

/**
 * @autor :JuRenZhao
 * @Date :Created in 16:38 2022/5/10
 * @Description :商品查询query
 */
public class ProductListQuery {
    private String keyword;

    private List<Integer> categorys;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Integer> categorys) {
        this.categorys = categorys;
    }
}
