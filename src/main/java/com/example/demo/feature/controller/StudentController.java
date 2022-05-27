package com.example.demo.feature.controller;

import com.example.demo.feature.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping(value = "/findStudent")
    public String getStudent() {
        System.out.println("findStudent");
        return  studentService.findByName();
    }

    @GetMapping("/getStudent")
    public String checkStudent(){
        System.out.println("getStudent");
        return "Hello checkStudent";
    }
}
