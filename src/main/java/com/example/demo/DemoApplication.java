package com.example.demo;

import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = {"com.example.demo.data.mybatis.mappers"})

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){return  new ModelMapper();}

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
