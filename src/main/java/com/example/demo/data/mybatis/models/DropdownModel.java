package com.example.demo.data.mybatis.models;

import lombok.Data;

@Data
public class DropdownModel {
    private String categories;
    private String valText;
    private String labelText;
    private Integer priorityOrder;
    private String activeFlag;
}
