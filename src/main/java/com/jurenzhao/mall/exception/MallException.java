package com.jurenzhao.mall.exception;

/**
 * @autor :JuRenZhao
 * @Date :Created in 8:45 2022/5/4
 * @Description :统一异常
 */
public class MallException extends Exception{
    Integer code;
    String msg;


    public MallException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MallException(MallExceptionEnum exceptionEnum){
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
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
