package com.example.demo.db.server;

import com.example.demo.db.mapper.StudentHomeworkMapper;
import com.example.demo.db.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHomeworkServer {
    private StudentHomeworkMapper studentHomeworkMapper;
    @Autowired
    public StudentHomeworkServer(StudentHomeworkMapper studentHomeworkMapper){
        this.studentHomeworkMapper = studentHomeworkMapper;
    }
    public List<StudentHomework> SelectAllStudentHomeworkBytitle(String title){
//        return null;
        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkBytitle(title);
    }

    public boolean InsertStudentHomework(String username,String title,String content){
        try {
            studentHomeworkMapper.InsertStudentHomework(username, title, content);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
