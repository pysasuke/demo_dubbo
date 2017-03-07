package com.py.demo.util;


import com.py.demo.enums.ResponseCode;

/**
 * 异常统一处理
 */
public class ServiceException extends RuntimeException {

    ResponseCode code;

    public ServiceException(ResponseCode code) {
        super();
        this.code = code;
    }

    public ServiceException(ResponseCode code, String message) {
        super(message);
        this.code = code;
    }

    public ResponseCode getCode() {
        return this.code;
    }
}
