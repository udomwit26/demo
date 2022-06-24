package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class FuzzBuzzService {

    public String getFuzzBuzz(Integer input) {

        if (isFuzzBuzz(input)) {
            return "FuzzBuzz";
        } else if (isFuzz(input)) {
            return "Fuzz";
        } else if (isBuzz(input)) {
            return "Buzz";
        } else {
            return String.valueOf(input);
        }
    }

    private boolean isFuzzBuzz(Integer input){
        return input % 3 == 0 && input % 5 == 0;
    }

    private boolean isFuzz(Integer input){
        return input % 3 == 0;
    }

    private boolean isBuzz(Integer input){
        return input % 5 == 0;
    }

}
