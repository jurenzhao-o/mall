package com.jurenzhao.mall.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @autor :JuRenZhao
 * @Date :Created in 20:31 2022/5/9
 * @Description :商品添加实体类
 */
public class AddProductReq {

    @NotNull(message = "商品名称不能为空")
    private String name;

    @NotNull(message = "商品图片不能为空")
    private String image;

    private String detail;

    @NotNull(message = "商品分类不能为空")
    private Integer categoryId;

    @NotNull(message = "商品价格不能为空")
    @Min(value = 1,message = "最低不能低于1分")
    private Integer price;

    @NotNull(message = "商品库存不能为空")
    @Max(value = 1000,message = "库存不能超过1000")
    private Integer stock;


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
