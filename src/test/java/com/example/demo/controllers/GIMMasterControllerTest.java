package com.example.demo.controllers;


import com.example.demo.dto.GimHeaderDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GIMMasterControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test add gim header ")
    //@Sql(value = {"/testdata/GimMasterControllerTest/clearGimHeader.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testAddGimMaster() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        GimHeaderDTO gimHeaderDTO = new GimHeaderDTO();
        gimHeaderDTO.setGimType("TONG1");
        gimHeaderDTO.setGimDesc("Test add Tong");
        gimHeaderDTO.setActiveFlag("Y");
        gimHeaderDTO.setCdLength(5);
        gimHeaderDTO.setField1Label("xxx");
        gimHeaderDTO.setField2Label("xxx");
        gimHeaderDTO.setField3Label("xxx");
        gimHeaderDTO.setCreatedBy("tong");
        //gimHeaderDTO.setModifiedBy("tong");

        GimHeaderDTO gimHeaderDTO2 = new GimHeaderDTO();
        gimHeaderDTO2.setGimType("TONG2");
        gimHeaderDTO2.setGimDesc("Test add Tong");
        gimHeaderDTO2.setActiveFlag("Y");
        gimHeaderDTO2.setCdLength(5);
        gimHeaderDTO2.setField1Label("xxx");
        gimHeaderDTO2.setField2Label("xxx");
        gimHeaderDTO2.setField3Label("xxx");
        gimHeaderDTO2.setCreatedBy("tong");
        //gimHeaderDTO2.setModifiedBy("tong");

        List<GimHeaderDTO>  gimHeaders = Arrays.asList(gimHeaderDTO,gimHeaderDTO2);

        HttpEntity<List<GimHeaderDTO>> requestEntity = new HttpEntity<>(gimHeaders, headers);

        ResponseEntity<String> response = restTemplate.exchange("/gimheader", HttpMethod.PUT,requestEntity,String.class);
        assertEquals("ERR",response.getBody());
    }

}