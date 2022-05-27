package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//@Autowired(required = true)
	//private StudentService studentService;

	//@Autowired(required = true)
	//StudentDao dao;
	/*
	@GetMapping("/hello")
	public String hello(){
		return "Hello tong";
	}

	@GetMapping("/hello2")
	public String hello2(){
		return "Hello tong2";
	}

	@GetMapping(value = "/getStudent/{firstName}")
	public String getStudent(@PathVariable("firstName") String firstName) {

		//return  studentService.findByName(firstName);

		return  "xx";
	}
	*/

}
