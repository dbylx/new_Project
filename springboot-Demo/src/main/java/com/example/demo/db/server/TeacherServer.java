package com.example.demo.db.server;

import com.example.demo.db.mapper.TeacherMapper;
import com.example.demo.db.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServer {
    private TeacherMapper teacherMapper;

    @Autowired
    public TeacherServer(TeacherMapper teacherMapper){
        this.teacherMapper = teacherMapper;
    }


    public boolean isLogin(String username, String password){
        System.out.println(username);
        System.out.println(password);
        Teacher teacher = teacherMapper.SeletOneTeacher(username);
        if(teacher == null){
            System.out.println("kong");
        }
        try {
            return teacher.getPassword().equals(password);
        }catch (Exception e){
            return false;
        }
    }


    public boolean addTeacher(String name,String username,String password){
        try {
            teacherMapper.InsertTeacher(name, username, password);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;

    }



}
