package com.example.demo.controllers;

import com.example.demo.data.mybatis.models.GimHeaderModel;
import com.example.demo.dto.GimHeaderDTO;
import com.example.demo.service.Gimmasterervice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GimMasterController {

    private final ModelMapper modelMapper;
    private final Gimmasterervice gimmasterervice;

    @PutMapping(value = "/gimheader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addGimHeader(@RequestBody final List<GimHeaderDTO> input, HttpServletRequest request) {

        //ModelMapper modelMapper = new ModelMapper();


        if (input == null) return "NERR";

        if (modelMapper == null) return "MERR";

        input.stream().forEach(e -> System.out.println("data :" + e.getGimDesc()));


        List<GimHeaderModel> gimDtos = input.stream().map(e->modelMapper.map(e,GimHeaderModel.class)).collect(Collectors.toList());


        gimDtos.stream().forEach(e->System.out.println("data :" +e.getGimDesc()));

        //try{
            gimDtos.stream().forEach(e->gimmasterervice.addlGimHeaders(e));
       // }catch (Exception ex){
       //     return "ERR";
        //}

        return "INF";

    }

}
