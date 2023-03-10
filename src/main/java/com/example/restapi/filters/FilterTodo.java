package com.example.restapi.filters;

import com.example.restapi.models.Student;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;

@WebFilter("/students/*")
public class FilterTodo implements Filter {
    public static String fromBufferToString(BufferedReader reader) {
        Stream<String> stream = reader.lines();

        return stream.reduce("", (acc, ele) -> acc + ele);
    }
    public static final Gson gson = new Gson();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try{
            BufferedReader reader = request.getReader();
            String body = fromBufferToString(reader);
            Student student = gson.fromJson(body,Student.class);
            request.setAttribute("student",student);
            chain.doFilter(request, response);
        }catch (Exception e){
            System.out.println("[Exeption Do Filter]" + e.getMessage());
        }

    }
}
