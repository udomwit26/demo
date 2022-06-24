package com.example.demo.data.mybatis.mappers;

import com.example.demo.data.mybatis.models.GimHeaderModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GimMasterMapper {
    List<GimHeaderModel> findAllGimHeaders();
}
