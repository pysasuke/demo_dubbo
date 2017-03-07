package com.py.demo.util;

import com.py.demo.common.BaseResult;
import com.py.demo.enums.ResponseCode;
import org.springframework.http.ResponseEntity;

public class ResponseUtil<T> {
    public static <T> ResponseEntity<BaseResult> ok(Object entity) {
        return ResponseEntity.ok(new BaseResult(entity));
    }

    public static <T> ResponseEntity<BaseResult> genResponse(String status, String message, Object entity) {
        return ResponseEntity.ok(new BaseResult(status, message, entity));
    }

    public static <T> ResponseEntity<BaseResult> genResponse(ResponseCode code) {
        return ResponseEntity.ok(new BaseResult(String.valueOf(code.getStatus()), code.getMessage(), null));
    }


}
