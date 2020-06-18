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

    public List<StudentHomework> SelectAllStudentHomeworkByUsername(String username){
//        return null;
//        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkByUsername(username);
    }

    public List<StudentHomework> SelectAllStudentHomeworkByUsernameTitle(String title, String username){
//        return null;
//        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkByUsernameTitle(title,username);
    }

    public List<StudentHomework> SelectAllStudentHomeworkByGood(){
//        return null;
//        System.out.println(title);
        return studentHomeworkMapper.SelectAllStudentHomeworkByGood();
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

    public boolean UpdateReview(StudentHomework studentHomework){
        try{
            studentHomeworkMapper.UpdateReview(studentHomework);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean UpdateHomework(StudentHomework studentHomework){
        try{
            studentHomeworkMapper.UpdateHomework(studentHomework);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
