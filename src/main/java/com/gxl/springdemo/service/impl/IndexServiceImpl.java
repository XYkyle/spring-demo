package com.gxl.springdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gxl.springdemo.mapper.DeptMapper;
import com.gxl.springdemo.service.IIndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IndexServiceImpl implements IIndexService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public String indexTest() {
        return JSONObject.toJSONString(deptMapper.selectAll());
    }
}
