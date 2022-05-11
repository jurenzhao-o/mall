package com.jurenzhao.mall.model.request;

/**
 * @autor :JuRenZhao
 * @Date :Created in 16:32 2022/5/10
 * @Description :商品列表请求参数
 */
public class ProductListReq {
    private String keyword;
    private Integer categoryId;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String orderBy;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
