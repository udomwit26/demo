package com.example.demo.controllers;

import com.example.demo.dto.DropdownDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProvinceDropdownControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test Case 1: Find all Provinces")
    @SneakyThrows
    @Sql(value = "ProvinceDropdownControllerTest/data_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "ProvinceDropdownControllerTest/data_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCase1() {
        ResponseEntity<List> response = restTemplate.exchange("/dropdown/provinces", HttpMethod.GET, null, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        List<DropdownDTO> allProvinceList = mapper.convertValue(response.getBody(), new TypeReference<List<DropdownDTO>>() {
        });
        assertEquals(4, allProvinceList.size());
    }

    @Test
    @SneakyThrows
    @DisplayName("Test Case 2: Find all active Provinces")
    @Sql(value = "ProvinceDropdownControllerTest/data_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "ProvinceDropdownControllerTest/data_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCase2() {
        ResponseEntity<List> response = restTemplate.exchange("/dropdown/active/provinces", HttpMethod.GET, null, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        List<DropdownDTO> allProvinceList = mapper.convertValue(response.getBody(), new TypeReference<List<DropdownDTO>>() {
        });
        assertEquals(3, allProvinceList.size());
    }

    @Test
    @SneakyThrows
    @DisplayName("Test Case 3: Find all active Provinces and check order")
    @Sql(value = "ProvinceDropdownControllerTest/data_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "ProvinceDropdownControllerTest/data_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCase3() {
        ResponseEntity<List> response = restTemplate.exchange("/dropdown/active/provinces", HttpMethod.GET, null, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ObjectMapper mapper = new ObjectMapper();
        List<DropdownDTO> allProvinceList = mapper.convertValue(response.getBody(), new TypeReference<List<DropdownDTO>>() {
        });
        assertEquals(3, allProvinceList.size());
        assertEquals("Krungthep",allProvinceList.get(0).getText());
        assertEquals("Nontaburi",allProvinceList.get(1).getText());
        assertEquals("Samut Prakarn",allProvinceList.get(2).getText());
    }
}
