package com.gxl.springdemo.config;

import com.gxl.springdemo.pojo.common.HttpMsgEnum;
import com.gxl.springdemo.pojo.common.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heimu
 * @Date 2021/9/8 下午8:50
 * @Version 1.0
 */
@ControllerAdvice(annotations = RestController.class ,basePackages = "com.gxl.springdemo.controller")
public class ExceptionAdvice {
    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> exceptionHandler(Exception e) {
        if(e instanceof IllegalArgumentException){
            log.error("参数异常:{}",e.getMessage(),e);
            return ResponseEntity.result(HttpMsgEnum.PARAM_ILLEGAL.getCode(), "参数异常:" + e.getMessage());
        }

        log.error("未知异常:" + e.getMessage(), e);
        return ResponseEntity.result(HttpMsgEnum.NOT_KNOW_ERROR.getCode(), "未知异常:" + e.getMessage());
    }

}
