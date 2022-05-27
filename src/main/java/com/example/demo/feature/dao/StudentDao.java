package com.example.demo.feature.dao;

import com.example.demo.feature.model.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentDao extends CrudRepository<Student,String> {


}
