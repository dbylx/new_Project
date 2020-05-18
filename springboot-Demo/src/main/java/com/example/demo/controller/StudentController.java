package com.example.demo.controller;


import com.example.demo.core.response.DataResponse;
import com.example.demo.db.server.HomeworkServer;
import com.example.demo.db.server.StudentHomeworkServer;
import com.example.demo.db.server.StudentServer;
import com.example.demo.db.server.TeacherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class StudentController {

    private final TeacherServer teacherServer;
    private final StudentServer studentServer;
    private final StudentHomeworkServer studentHomeworkServer;
    private final HomeworkServer homeworkServer;
    @Autowired
    public StudentController(TeacherServer teacherServer,StudentServer studentServer,StudentHomeworkServer studentHomeworkServer,HomeworkServer homeworkServer){
        this.teacherServer = teacherServer;
        this.studentServer = studentServer;
        this.studentHomeworkServer = studentHomeworkServer;
        this.homeworkServer = homeworkServer;
    }

    @RequestMapping("/viewHomework")
    public String viewHomework(){
        return "studentQueryHomework";
    }

    @RequestMapping("/submitHomeworkJump")
    public String submitHomeworkJump(){
        return "submitHomework";
    }

    @RequestMapping("/submitHomework")
    @ResponseBody
    public DataResponse submitHomework(String username,String title,String content) {
        System.out.println(username);
        System.out.println(title);
        System.out.println(content);
        boolean flag = studentHomeworkServer.InsertStudentHomework(username, title, content);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setFlag(flag);
        return dataResponse;
    }

}
