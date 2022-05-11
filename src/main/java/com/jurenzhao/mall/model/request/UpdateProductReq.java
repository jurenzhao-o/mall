package com.jurenzhao.mall.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @autor :JuRenZhao
 * @Date :Created in 20:31 2022/5/9
 * @Description :商品添加实体类
 */
public class UpdateProductReq {

    @NotNull(message = "商品编号不能为空")
    private Integer id;

    private String name;

    private String image;

    private String detail;

    private Integer categoryId;

    @Min(value = 1,message = "最低不能低于1分")
    private Integer price;

    @Max(value = 1000,message = "库存不能超过1000")
    private Integer stock;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
