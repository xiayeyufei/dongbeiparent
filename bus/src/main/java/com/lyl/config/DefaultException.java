package com.lyl.config;


import com.lyl.util.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultException {
    @ExceptionHandler
    public ResultJson defaultExceptionHandler(Exception e){
        /**
         * 魔法数字：switch条件，123,1咋样2咋样。。。除了设计者本人，其它人都看不懂
         */
        e.printStackTrace();
        return ResultJson.error(e.getMessage());
    }
}
