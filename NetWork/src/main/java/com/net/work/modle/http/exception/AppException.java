package com.net.work.modle.http.exception;

/**
 * Created by ruibing.han on 2018/3/26.
 */

public class AppException extends Exception{

    private String msg;

    private String code;

    public AppException(String message,String code) {
        this.msg = message;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
