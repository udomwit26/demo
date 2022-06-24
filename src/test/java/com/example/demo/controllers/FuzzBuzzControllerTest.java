package com.example.demo.controllers;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FuzzBuzzControllerTest {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    @DisplayName("Test Case 1: Input 1 then Output = 1 ")
    void testCast1() {
        String output = restTemplate.getForObject("/fuzzbuzz/1", String.class);
        assertEquals("1", output);
    }

    @Test
    @DisplayName("Test Case 2: Input 2 then Output = 2 ")
    void testCast2() {
        String output = restTemplate.getForObject("/fuzzbuzz/2", String.class);
        assertEquals("2", output);
    }

    @Test
    @DisplayName("Test Case 3: Input 3 then Output = Fuzz ")
    void testCast3() {
        String output = restTemplate.getForObject("/fuzzbuzz/3", String.class);
        assertEquals("Fuzz", output);
    }

    @Test
    @DisplayName("Test Case 4: Input 4 then Output = 4 ")
    void testCast4() {
        String output = restTemplate.getForObject("/fuzzbuzz/4", String.class);
        assertEquals("4", output);
    }

    @Test
    @DisplayName("Test Case 5: Input 5 then Output = Buzz ")
    void testCast5() {
        String output = restTemplate.getForObject("/fuzzbuzz/5", String.class);
        assertEquals("Buzz", output);
    }

    @Test
    @DisplayName("Test Case 6: Input 10 then Output = Buzz ")
    void testCast6() {
        String output = restTemplate.getForObject("/fuzzbuzz/10", String.class);
        assertEquals("Buzz", output);
    }

    @Test
    @DisplayName("Test Case 7: Input 12 then Output = Fuzz ")
    void testCast7() {
        String output = restTemplate.getForObject("/fuzzbuzz/12", String.class);
        assertEquals("Fuzz", output);
    }

    @Test
    @DisplayName("Test Case 8: Input 13 then Output = 13 ")
    void testCast8() {
        String output = restTemplate.getForObject("/fuzzbuzz/13", String.class);
        assertEquals("13", output);
    }

    @Test
    @DisplayName("Test Case 9: Input 15 then Output = FuzzBuzz ")
    void testCast9() {
        String output = restTemplate.getForObject("/fuzzbuzz/15", String.class);
        assertEquals("FuzzBuzz", output);
    }

    @Test
    @DisplayName("Test Case 10: Input 20 then Output = Buzz ")
    void testCast10() {
        String output = restTemplate.getForObject("/fuzzbuzz/20", String.class);
        assertEquals("Buzz", output);
    }

    @Test
    @DisplayName("Test Case 11: Input 45 then Output = FuzzBuzz ")
    void testCast11() {
        String output = restTemplate.getForObject("/fuzzbuzz/45", String.class);
        assertEquals("FuzzBuzz", output);
    }

    @Test
    @DisplayName("Test Case 12: Input 150 then Output = FuzzBuzz ")
    void testCast12() {
        String output = restTemplate.getForObject("/fuzzbuzz/150", String.class);
        assertEquals("FuzzBuzz", output);
    }


    @Test
    @DisplayName("Test Case 13: Input 8885 then Output = Buzz ")
    void testCast13() {
        String output = restTemplate.getForObject("/fuzzbuzz/8885", String.class);
        assertEquals("Buzz", output);
    }

    @Test
    @DisplayName("Test Case 14: Input 9999 then Output = Fuzz ")
    void testCast14() {
        String output = restTemplate.getForObject("/fuzzbuzz/9999", String.class);
        assertEquals("Fuzz", output);
    }


    @Test
    @DisplayName("Test Case 1: Input 1 then Output = 1 ")
    void testCast15() {
        String output = restTemplate.getForObject("/fuzzbuzz/1", String.class);
        assertEquals("1", output);
    }
}
