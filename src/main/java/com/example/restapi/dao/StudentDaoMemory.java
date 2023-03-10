package com.example.restapi.dao;


import com.example.restapi.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoMemory implements Persistence {
    public static List<Student> students;
    private static StudentDaoMemory studentDaoMemory;

    private StudentDaoMemory() {
        students = new ArrayList<>();
    }

    public static StudentDaoMemory getInstance() {

        if (studentDaoMemory == null)
            studentDaoMemory = new StudentDaoMemory();

        return studentDaoMemory;
    }


    @Override
    public Student save(Student student) {
        if (student.getCne().equals("") || student.getCne() == null || student.getName().equals("") || student.getName() == null)
            return null;

        students.add(student);
        return student;
    }

    @Override
    public Student findById(String cne) {
        for (Student student:students) {
            if(student.getCne().equals(cne))
                return student;
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public Student update(Student student) {
        for (Student student1:students) {
            if(student1.getCne().equals(student.getCne())){
                student1.setName(student.getName());
                return student;
            }
        }
        return null;
    }

    @Override
    public Student delete(String cne) {
        for (Student student1 : students) {
            if (student1.getCne().equals(cne)) {
                students.remove(student1);
                return student1;
            }
        }
        return null;
    }
}
