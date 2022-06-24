package com.dxc.application.controllers;

import com.dxc.application.constants.AppConstants;
import com.dxc.application.feature.gimmaster.data.database.model.GimHeader;
import com.dxc.application.model.Combo;
import com.dxc.application.model.GimHeader;
import com.dxc.application.model.RestJsonData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlConfig(dataSource = "myBatisDataSource",transactionManager = "mybastistx")
class GIMMasterControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @SneakyThrows
    @Test
    @DisplayName("Test search gim header with search criteria gim type")
    @Sql(value = {"/testdata/GimMasterControllerTest/testGimMaster.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testSearchGimMasterWithSearchGimType() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        GimHeader gimCriteria = new GimHeader();
        gimCriteria.setSearchGimTypes(new String[]{"TEST_GIM"});
        HttpEntity<GimHeader> requestEntity = new HttpEntity<>(gimCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.postForEntity("/gimmaster/gimheader",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        List<GimHeader> gimHeaders = mapper.convertValue(response.getBody().getData(),new TypeReference<List<GimHeader>>(){});
        assertEquals(1,gimHeaders.size());
        assertTrue(gimHeaders.stream().anyMatch(item -> "TEST_GIM".equals(item.getGimType())));
    }

    @Test
    @DisplayName("Test add gim header ")
    //@Sql(value = {"/testdata/GimMasterControllerTest/clearGimHeader.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testAddGimMaster() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        GimHeader gimCriteria = new GimHeader();
        gimCriteria.setGimType("TONG");
        gimCriteria.setGimDesc("Test add Tong");
        gimCriteria.setActiveFlag("Y");
        gimCriteria.setCdLength(new BigDecimal(5));
        gimCriteria.setField1Label("xxx");
        gimCriteria.setField2Label("xxx");
        gimCriteria.setField3Label("xxx");
        gimCriteria.setMode(AppConstants.MODE_ADD);

        HttpEntity<GimHeader> requestEntity = new HttpEntity<>(gimCriteria, headers);

        ResponseEntity<RestJsonData> response = restTemplate.exchange("/gimmaster/gimheader", HttpMethod.PUT,requestEntity,RestJsonData.class);
        //ResponseEntity<RestJsonData> response = restTemplate.put("/gimmaster/gimheader",requestEntity,RestJsonData.class);

        ObjectMapper mapper = new ObjectMapper();
        BigDecimal rowInsert = mapper.convertValue(response.getBody().getRowCount(),new TypeReference<BigDecimal>(){});
        assertEquals(1,rowInsert.intValue());
        //assertTrue(gimHeaders.stream().anyMatch(item -> "TEST_GIM".equals(item.getGimType())));
    }

}