package com.example.restapi.dao;


import com.example.restapi.models.Student;

import java.util.List;

public interface Persistence {
    Student save(Student student);
    Student findById(String cne);
    List<Student> getAll();
    Student update(Student student);
    Student delete(String cne);
}
