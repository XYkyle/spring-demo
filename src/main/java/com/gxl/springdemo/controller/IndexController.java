package com.gxl.springdemo.controller;

import com.gxl.springdemo.service.IIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(tags = "测试swagger")
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private IIndexService indexService;


    @GetMapping("/test")
    @ApiOperation("测试方法")
    public String indexTest(){


        log.info("===查询数据==");
        return indexService.indexTest();
    }


}
