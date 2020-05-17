package com.example.demo.db.server;

import com.example.demo.db.mapper.StudentMapper;
import com.example.demo.db.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServer {
    private StudentMapper studentMapper;
    @Autowired
    public StudentServer(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}
