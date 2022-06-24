package com.example.demo.data.mybatis.models;

import lombok.Data;

import java.util.Date;

@Data
public class GimHeaderModel {
    private String gimType;
    private String gimCd;
    private String gimDesc;
    private Integer cdLength;
    private String field1Label;
    private String field2Label;
    private String field3Label;
    private String activeFlag;
    private String createdBy;
    private Date createdDt;
    private String modifiedBy;
    private Date modifiedDt;
}
