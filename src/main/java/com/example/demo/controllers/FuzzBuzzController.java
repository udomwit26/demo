package com.example.demo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fuzzbuzz")
@Slf4j
public class FuzzBuzzController {


    private final FuzzBuzzService fuzzBuzzService;

    @GetMapping("{input}")
    public String getFuzzBuzz(@PathVariable("input") String input) {

        log.info("input:{}",input);
        String validateMessage = null;
        int numFuzzBuzz = 0;

        try {
            numFuzzBuzz = Integer.parseInt(input);

            if(isInterLessThanZeroAndMoreThanThousand(numFuzzBuzz)){
                validateMessage = "invalid number";
            }

        }catch (Exception e){
            validateMessage = "invalid number";
        }

        if(validateMessage != null){
            return  validateMessage;
        }
        return fuzzBuzzService.getFuzzBuzz(numFuzzBuzz);
    }

    private boolean isInterLessThanZeroAndMoreThanThousand(Integer input){
        return  input <= 0 || input >= 10000;
    }

}
