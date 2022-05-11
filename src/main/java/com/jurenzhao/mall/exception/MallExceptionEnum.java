package com.jurenzhao.mall.exception;

/**
 * @autor :JuRenZhao
 * @Date :Created in 8:39 2022/5/4
 * @Description :异常枚举
 */
public enum MallExceptionEnum {
    NEED_USER_NAME(10001,"用户名不能为空"),
    NEED_PASSWORD(10002,"密码不能为空"),
    EXIT_USER(10003,"用户存在"),
    INSERT_USER_FAIL(10004,"新增用户失败"),
    PASSWORD_SHORT(10005,"密码少于8位"),
    PASSWORD_ERROR(10006,"密码错误"),
    NEED_LOGIN(10007,"用户未登录"),
    UPDATE_FAIL(10008,"更新失败"),
    NEED_ADMIN(10009,"无管理员权限"),
    PARAM_NULL(10010,"参数为空"),
    REQUEST_PARAM_ERROR(10011,"请求参数错误"),
    NAME_EXITS(10012,"分类名称存在"),
    CREATE_FAIL(10013,"新增失败"),
    CATEGORY_NOTFIND(10014,"信息有误"),
    DELETE_FAIL(10015,"删除失败"),
    EXIT_PRODUCT(10016,"商品已存在"),
    UPLOAD_FILE_FAIL(10017,"上传文件失败"),
    NOEXIT_PRODUCT(10018,"商品不存在"),
    SYSTEM_ERROR(20000,"系统错误请从控制台或者日志文件查看");

    /**
     * 异常编码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    MallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
