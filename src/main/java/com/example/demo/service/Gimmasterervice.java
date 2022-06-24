package com.example.demo.service;

import com.example.demo.data.mybatis.mappers.GimMasterMapper;
import com.example.demo.data.mybatis.models.GimHeaderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Gimmasterervice {
    private final GimMasterMapper gimMasterMapper;

    @Transactional(rollbackFor = Exception.class)
    public void addlGimHeaders(GimHeaderModel model){
        gimMasterMapper.insertGimHeader(model);

    }


}
