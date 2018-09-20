package com.example.demo.common;

import lombok.Data;

public class RRException extends RuntimeException{

    private String message;
    private int code=500;

    public RRException() {
    }

    public RRException(String message) {
        super(message);
        this.message = message;
    }

    public RRException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public RRException(Throwable cause) {
        super(cause);
    }

    public RRException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
