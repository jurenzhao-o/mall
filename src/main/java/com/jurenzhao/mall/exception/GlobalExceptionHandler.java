package com.jurenzhao.mall.exception;

import com.jurenzhao.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor :JuRenZhao
 * @Date :Created in 9:42 2022/5/4
 * @Description :处理统一异常的handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception ex){
        logger.error("Default Exception"+ex);
        return ApiRestResponse.error(MallExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(MallException.class)
    @ResponseBody
    public Object handleMallException(MallException ex){
        logger.error("MallException"+ex);
        return ApiRestResponse.error(ex.getCode(),ex.getMsg());
    }
    //
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiRestResponse handMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        logger.error("MethodArgumentNotValidException"+ex);
        return handBindingResult(ex.getBindingResult());
    }

    private ApiRestResponse handBindingResult(BindingResult bindingResult){
        List<String> list = new ArrayList<>();
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                list.add(allError.getDefaultMessage());
            }
        }
        if(CollectionUtils.isEmpty(list)){
            return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR);
        }else {
            return ApiRestResponse.error(MallExceptionEnum.REQUEST_PARAM_ERROR.getCode(),list.toString());
        }
    }
}
