package com.gxl.springdemo.controller;

import com.gxl.springdemo.pojo.WhiteDo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class WhiteTask implements Callable<Boolean> {

    private List<WhiteDo> whiteDoList = new ArrayList();

    public WhiteTask(List<WhiteDo> whiteDoList) {
        this.whiteDoList = whiteDoList;
    }

    public WhiteTask() {
    }

    @Override
    public Boolean call() throws Exception {
        //整体添加字段
        for (WhiteDo whiteDo : whiteDoList) {
            log.info("{}===>:{}",Thread.currentThread().getName(),whiteDo.toString());
        }
        //向rds,ex,solr添加数据


        return Boolean.TRUE;
    }
}
