package com.example.demo.data.mybatis.mappers;

import com.example.demo.data.mybatis.models.DropdownModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DropdownMapper {

    List<DropdownModel> findByProvinces(@Param("valText") final String valText);
    List<DropdownModel> findAllProvinces(@Param("activeFlag") final String activeFlag);
}
