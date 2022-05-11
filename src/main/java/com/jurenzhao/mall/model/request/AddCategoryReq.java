package com.jurenzhao.mall.model.request;

import org.mybatis.spring.annotation.MapperScan;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @autor :JuRenZhao
 * @Date :Created in 18:59 2022/5/6
 * @Description :商品添加实体
 */
public class AddCategoryReq {

    @NotNull(message = "name参数不能为空")
    private String name;

    @NotNull(message = "type参数不能为空")
    @Max(3)
    private Integer type;

    @NotNull(message = "parentId参数不能为空")
    private Integer parentId;

    @NotNull(message = "orderNum参数不能为空")
    private Integer orderNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
