package com.jurenzhao.mall.common;

import com.jurenzhao.mall.exception.MallExceptionEnum;

/**
 * @autor :JuRenZhao
 * @Date :Created in 8:47 2022/5/4
 * @Description :统一返回对象
 */
public class ApiRestResponse <T> {

    Integer code;
    String msg;
    T data;

    private static final Integer OK_CODE = 10000;
    private static final String  OK_MSG = "success";

    public ApiRestResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiRestResponse(){
        this(OK_CODE,OK_MSG);
    }

    public static <T> ApiRestResponse<T> success(){
        return new ApiRestResponse();
    }

    public static <T> ApiRestResponse<T> success(T data){
        ApiRestResponse<T> apiRestResponse = new ApiRestResponse();
        apiRestResponse.setData(data);
        return apiRestResponse;
    }

    public static <T> ApiRestResponse<T> error(Integer code, String msg){
        return new ApiRestResponse(code,msg);
    }

    public static <T> ApiRestResponse<T> error(MallExceptionEnum mallExceptionEnum){
        return new ApiRestResponse(mallExceptionEnum.getCode(),mallExceptionEnum.getMsg());
    }

    @Override
    public String toString() {
        return "ApiRestResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
