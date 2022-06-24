package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DropdownMasterDTO {

    private String categories;
    private String valText;
    private String labelText;
    private Integer priorityOrder;
    private String activeFlag;
}
