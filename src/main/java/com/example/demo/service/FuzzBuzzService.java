package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class FuzzBuzzService {

    public String getFuzzBuzz(Integer input) {

        if (input % 3 == 0 && input % 5 == 0) {
            return "FuzzBuzz";
        } else if (input % 3 == 0) {
            return "Fuzz";
        } else if (input % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(input);
        }
    }

}
