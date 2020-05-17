package com.example.demo.controller;

import com.example.demo.db.model.Student;
import com.example.demo.db.server.StudentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
//

    private final StudentServer studentServer;

    public APIController(StudentServer studentServer) {
        this.studentServer = studentServer;
    }

    @RequestMapping("/student")
    public List<Student> getObject(){
        return studentServer.findAll();
    }
}
