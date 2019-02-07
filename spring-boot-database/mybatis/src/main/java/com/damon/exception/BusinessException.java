package com.damon.exception;

/**
 * 异常处理业务类
 *
 * @author Damon Chen
 * @date 2018/11/24
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message){
        super(message);
    }
}
