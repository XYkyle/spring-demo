package com.gxl.springdemo.pojo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应体
 * {@link com.gxl.springdemo.pojo.common.HttpMsgEnum}
 */
@Data
public class ResponseEntity<T> implements Serializable {

    //响应编码
    private String code;
    //响应信息
    private String message;
    //响应数据
    private T data;
    //全链路追踪
    private String traceId;

    public ResponseEntity() {
        this(HttpMsgEnum.SUCCESS,null);
    }

    public ResponseEntity(T data) {
        this(HttpMsgEnum.SUCCESS,data);
    }

    public ResponseEntity(HttpMsgEnum httpMsgEnum) {
        this(httpMsgEnum,null);
    }

    public ResponseEntity(String message){
        this(message,null);
    }

    public ResponseEntity(String message, T data) {
        this(HttpMsgEnum.SUCCESS.getCode(),message,data);
    }
    public ResponseEntity(HttpMsgEnum httpMsgEnum, T data){
        this(httpMsgEnum.getCode(),httpMsgEnum.getMsg(),data);
    }

    public ResponseEntity(String code , String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
//        this.traceId = LoggerContext.getTraceId();
    }

    public static <T> ResponseEntity<T> result(String code , String message, T data){
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(code);
        responseEntity.setMessage(message);
        responseEntity.setData(data);
//        resultEntity.setTraceId(LoggerContext.getTraceId());
        return responseEntity;
    }

    public static <T> ResponseEntity<T> result(String code , String message){
        return result(code,message,null);
    }

    public static <T> ResponseEntity<T> result(HttpMsgEnum httpMsgEnum, T data){
        return result(httpMsgEnum.getCode(),httpMsgEnum.getMsg(),data);
    }

    public static <T> ResponseEntity<T> SUCCESS() {
        return result(HttpMsgEnum.SUCCESS,null);
    }

    public static <T> ResponseEntity<T> SUCCESS(T data) {
        return result(HttpMsgEnum.SUCCESS,data);
    }

    public static <T> ResponseEntity<T> SUCCESS(String message, T data){
        return result(HttpMsgEnum.SUCCESS.getCode(),message,data);
    }

    public static <T> ResponseEntity<T> SUCCESS(String message){
        return result(HttpMsgEnum.SUCCESS.getCode(),message,null);
    }

    public static <T> ResponseEntity<T> ERROR() {
        return result(HttpMsgEnum.NOT_KNOW_ERROR,null);
    }

    public static <T> ResponseEntity<T> ERROR(T data) {
        return result(HttpMsgEnum.NOT_KNOW_ERROR,data);
    }

    public static <T> ResponseEntity<T> ERROR(String message, T data){
        return result(HttpMsgEnum.NOT_KNOW_ERROR.getCode(),message,data);
    }

    public static <T> ResponseEntity<T> ERROR(String message){
        return result(HttpMsgEnum.NOT_KNOW_ERROR.getCode(),message,null);
    }


}
