package com.example.demo.service;

import com.example.demo.data.mybatis.mappers.DropdownMapper;
import com.example.demo.data.mybatis.models.DropdownModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DropdownService {
    private final DropdownMapper dropdownMapper;

    @Transactional(readOnly = true)
    public List<DropdownModel> getAllProvinces(){
        return dropdownMapper.findAllProvinces(null);
    }

    @Transactional(readOnly = true)
    public List<DropdownModel> getAllActiveProvinces(String activeFlag){
        return dropdownMapper.findAllProvinces(activeFlag);
    }

    @Transactional(readOnly = true)
    public List<DropdownModel> findByProvinces(String valText){
        return dropdownMapper.findByProvinces(valText);
    }
}
