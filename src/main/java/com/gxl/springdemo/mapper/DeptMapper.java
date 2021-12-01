package com.gxl.springdemo.mapper;

import com.gxl.springdemo.pojo.domain.Dept;

import java.util.List;

public interface DeptMapper {

    int insert(Dept record);

    int insertSelective(Dept record);

    List<Dept> selectAll();


}
