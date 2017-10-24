package com.lottery.controller;

import com.lottery.common.kit.AESKit;
import com.lottery.common.response.Message;
import com.lottery.common.response.ResponseWrapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangyaohai on 2017/10/24.
 */
public abstract class BaseController {
    @Autowired
    protected AESKit aes;

    protected String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        return StringUtils.isNotBlank(ip) ? ip.split(",")[0] : null;
    }

    public ResponseEntity writeResponse(Object data) {
        return new ResponseEntity(new ResponseWrapper<>(data), HttpStatus.OK);
    }

    public ResponseEntity writeResponse(Message message) {

        return new ResponseEntity<>(new ResponseWrapper<>(message), HttpStatus.OK);
    }

    public ResponseEntity writeResponse(Message message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseWrapper<>(message), httpStatus);
    }

    public ResponseEntity writeResponse(Message message, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseWrapper<>(message, data), httpStatus);
    }

    public ResponseEntity writeSuccess(Object data) {
        return writeResponse(Message.SUCCESS, data, HttpStatus.OK);
    }
}
