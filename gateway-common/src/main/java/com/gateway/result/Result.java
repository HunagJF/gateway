package com.gateway.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; //编码：0成功，1和其它数字为失败
    private String message; //错误信息
    private T data; //数据
    private int total;//条数

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 0;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> success(T object, int total) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 0;
        result.total = total;
        return result;
    }

    public static <T> Result<T> error(String message) {
        Result result = new Result();
        result.message = message;
        result.code = 1;
        return result;
    }

}
