package com.app.standard.modle.http.exception;

//  Created by ruibing.han on 2018/3/29.

public class AppException extends Exception {

    private String errorCode;

    private String msg;

    public AppException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.msg = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
