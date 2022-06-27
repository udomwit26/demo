package com.example.demo.feature.common.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ServePageControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test Case 1: Test load HTML of home screen success")
    @SneakyThrows
    void testCase1() {

        ResponseEntity<String> response = restTemplate.exchange("/pages/home", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(StringUtils.endsWith(response.getBody(),"</html>"));


        ObjectMapper mapper = new ObjectMapper();
        String html = mapper.convertValue(response.getBody(), new TypeReference<String>() {});
        log.info("html:{}",html);

    }

    @Test
    @DisplayName("Test Case 2: Test load JS of home screen success")
    @SneakyThrows
    void testCase2() {

        ResponseEntity<String> response = restTemplate.exchange("/pages/home/js/home.js", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(StringUtils.endsWith(response.getBody(),"//]]>"));

    }

}