package com.example.demo;

import com.example.demo.data.mybatis.models.GimHeaderModel;
import com.example.demo.dto.GimHeaderDTO;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static  void main(String a[]){

        ModelMapper modelMapper = new ModelMapper();

        System.out.println("hello");

        GimHeaderDTO gimHeaderDTO = new GimHeaderDTO();
        gimHeaderDTO.setGimType("TONG");
        gimHeaderDTO.setGimDesc("Test add Tong 1");
        gimHeaderDTO.setActiveFlag("Y");
        gimHeaderDTO.setCdLength(5);
        gimHeaderDTO.setField1Label("xxx");
        gimHeaderDTO.setField2Label("xxx");
        gimHeaderDTO.setField3Label("xxx");

        GimHeaderDTO gimHeaderDTO2 = new GimHeaderDTO();
        gimHeaderDTO2.setGimType("TONG");
        gimHeaderDTO2.setGimDesc("Test add Tong 2");
        gimHeaderDTO2.setActiveFlag("Y");
        gimHeaderDTO2.setCdLength(5);
        gimHeaderDTO2.setField1Label("xxx");
        gimHeaderDTO2.setField2Label("xxx");
        gimHeaderDTO2.setField3Label("xxx");

        List<GimHeaderDTO> gimDtos = Arrays.asList( gimHeaderDTO,gimHeaderDTO2);

        GimHeaderModel gimModel = modelMapper.map(gimHeaderDTO,GimHeaderModel.class);
        List<GimHeaderModel> gimModels  =  gimDtos.stream().map(e->modelMapper.map(e,GimHeaderModel.class)).collect(Collectors.toList());

        System.out.println("hello:"+gimModel.getGimDesc());

        gimModels.stream().forEach(e->System.out.println("data :" +e.getGimDesc()));
    }
}
