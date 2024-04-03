package com.cloudengine.vo;

public class Result<T> {
    private T data;

    private String msg;

    private int code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Result success(T t) {
        code = 0;
        msg = "sucess";
        data = t;
        return this;
    }

    public Result failed(T b) {
        code = 1;
        msg = "failed";
        data = b;
        return this;
    }
}
