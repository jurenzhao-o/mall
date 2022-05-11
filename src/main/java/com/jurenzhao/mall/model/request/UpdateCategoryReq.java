package com.jurenzhao.mall.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @autor :JuRenZhao
 * @Date :Created in 18:59 2022/5/6
 * @Description :商品更新实体
 */
public class UpdateCategoryReq {
    @NotNull(message = "id不能为空")
    private Integer id;

    @NotNull(message = "name参数不能为空")
    @Size(min = 2,max = 5)
    private String name;

    @Max(3)
    private Integer type;

    private Integer parentId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
