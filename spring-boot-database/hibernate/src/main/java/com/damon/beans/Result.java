package com.damon.beans;

/**
 * @author Damon Chen
 * @date 2018/11/24
 */
public class Result {

    public Integer status;
    public String message;

    public Result(){}

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
