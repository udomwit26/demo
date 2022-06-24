package com.example.demo.controllers;

import com.example.demo.data.mybatis.models.DropdownModel;
import com.example.demo.dto.DropdownDTO;
import com.example.demo.services.DropdownService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProvinceDropdownController {
    private final DropdownService dropdownService;

    @GetMapping("/dropdown/provinces")
    public List<DropdownDTO> getAllProvinces() {
        return dropdownService.getAllProvinces().stream().map(this::convertDropdownModelToDTO).collect(Collectors.toList());
    }
    @GetMapping("/dropdown/active/provinces")
    public List<DropdownDTO> getAllActiveProvinces() {
        return dropdownService.getAllActiveProvinces().stream().map(this::convertDropdownModelToDTO).collect(Collectors.toList());
    }

    private DropdownDTO convertDropdownModelToDTO(DropdownModel model){
        return new DropdownDTO(model.getValText(), model.getLabelText());
    }
}
