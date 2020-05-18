package com.example.demo.db.server;

import com.example.demo.db.mapper.StudentMapper;
import com.example.demo.db.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServer{
    private StudentMapper studentMapper;
    @Autowired
    public StudentServer(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }
    public List<Student> findAllStudent(){
//        return null;
        return studentMapper.selectAll();
    }

    public boolean isLogin(String username, String password){
        Student student = studentMapper.SeletOneStudent(username);
        if(student == null){
            return false;
        }
        return student.getPassword().equals(password);
    }

    public boolean addStudent(String name,String username,String password){
        try {
            studentMapper.InsertStudent(name, username, password);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;

    }


}
