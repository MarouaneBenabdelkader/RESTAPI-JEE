package com.example.restapi.models;

public class Student {
    private String name;
    private String cne;

    public Student() {
    }

    public Student(String name, String cne) {
        this.name = name;
        this.cne = cne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }
}
