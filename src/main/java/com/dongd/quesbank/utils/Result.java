package com.dongd.quesbank.utils;

import lombok.Data;

@Data
public class Result <T>{
    private Integer code;

    private String message;

    private T data;

    public Result() {
    }
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if(data!=null){
            result.setData(data);
        }
        return result;
    }
    public static <T> Result<T> build(T body,Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> Result<T> ok(T data){
        Result<T>result=build(data);
        return build(data,ResultCodeEnum.SUCCESS);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}
