package com.example.restapi.controllers;

import com.example.restapi.dao.Persistence;
import com.example.restapi.dao.StudentDaoMemory;
import com.example.restapi.models.Student;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/students/*")
public class StudentRestApi extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        Persistence service = StudentDaoMemory.getInstance();
        if(req.getPathInfo() != null){
            String path = req.getPathInfo();
            String[] pathSplit = path.split("/");
            System.out.println(pathSplit.length);
            String cne = pathSplit[1];
            Student student = service.findById(cne);
            if(student == null){
                resp.setStatus(404);
                resp.getWriter().print("User not found");
                return;
            }
            resp.setStatus(200);
            resp.getWriter().print(gson.toJson(student));
            return;
        }
        resp.getWriter().print(gson.toJson(service.getAll()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        Student student =(Student) req.getAttribute("student");
        if(student.getCne().equals("") || student.getName().equals("")){
            resp.setStatus(400);
            resp.sendError(400);
            resp.getWriter().print("Invalid Format");
        }
        StudentDaoMemory.getInstance().save(student);
        resp.setStatus(201);
        resp.getWriter().print(gson.toJson(student));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        Student student =(Student) req.getAttribute("student");
        if(student.getCne().equals("") || student.getName().equals("")){
            resp.setStatus(400);
            resp.sendError(400);
            resp.getWriter().print("Invalid Format");
            return;
        }
        StudentDaoMemory.getInstance().update(student);
        resp.setStatus(201);
        resp.getWriter().print(gson.toJson(student));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        if(req.getPathInfo() != null){
            String path = req.getPathInfo();
            String[] pathSplit = path.split("/");
            System.out.println(pathSplit.length);
            String cne = pathSplit[1];
            Student student = StudentDaoMemory.getInstance().findById(cne);
            if(student == null){
                resp.setStatus(404);
                resp.getWriter().print("Not Found");
                return;
            }
            StudentDaoMemory.getInstance().delete(cne);
            resp.setStatus(200);
            resp.getWriter().print(gson.toJson(student));
        }

    }
}
