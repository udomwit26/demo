package com.example.demo.feature.login.controllers;

import com.example.demo.feature.login.dto.LoginDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class LoginControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test Case 1: Test load HTML of home screen success")
    @SneakyThrows
    void testCase1() {

        HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(new LoginDTO("testfail", "1234@abcd"));
        ResponseEntity<Boolean> response = restTemplate.exchange("/login", HttpMethod.POST, requestEntity, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Boolean.FALSE, response.getBody());
        log.info("html:{}", response.getBody());

    }

    @Test
    @DisplayName("Test Case 1: Test load HTML of home screen success")
    @SneakyThrows
    void testCase2() {

        HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(new LoginDTO("testpass", "1234@abcd"));
        ResponseEntity<Boolean> response = restTemplate.exchange("/login", HttpMethod.POST, requestEntity, Boolean.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Boolean.TRUE, response.getBody());
        log.info("html:{}", response.getBody());

    }
}