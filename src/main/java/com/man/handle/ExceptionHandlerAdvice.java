package com.man.handle;

import com.man.common.RestResult;
import com.man.exception.SchoolException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(SchoolException.class)
    public RestResult handleSZException(SchoolException e){
        RestResult restResult = new RestResult();
        restResult.put(RestResult.CODE,e.getCode());
        restResult.put(RestResult.MESSAGE,e.getMsg());
        return restResult;
    }

    /**
     * 主键重复异常
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public RestResult handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(),e);
        return RestResult.error("数据库中已经存在该记录");
    }

    /**
     * 全局异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public RestResult handleException(Exception e){
        log.error(e.getMessage(),e);
        return RestResult.error();
    }
}
