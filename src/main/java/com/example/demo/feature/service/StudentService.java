package com.example.demo.feature.service;

import com.example.demo.feature.dao.StudentDao;
import com.example.demo.feature.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;


    public String findByName(){

        List<Student> resultList = new ArrayList<Student>();
        studentDao.findAll().forEach(resultList::add);
        Student model = resultList.get(0);
        return model.getFirstName() + " : " + model.getLastName();


    }
}
