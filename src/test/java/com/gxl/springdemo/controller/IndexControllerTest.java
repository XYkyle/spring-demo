package com.gxl.springdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.gxl.springdemo.SpringDemoApplication;
import com.gxl.springdemo.service.IIndexService;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringDemoApplication.class})
public class IndexControllerTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IIndexService indexService;

    @Test
    public void indexTestTest(){
        log.info(()->"===>返回值:"+JSONObject.toJSONString(indexService.indexTest()));
    }

}