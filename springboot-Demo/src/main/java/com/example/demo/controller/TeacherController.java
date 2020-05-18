package com.example.demo.controller;

import com.example.demo.core.response.DataResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.server.HomeworkServer;
import com.example.demo.db.server.StudentHomeworkServer;
import com.example.demo.db.server.StudentServer;
import com.example.demo.db.server.TeacherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class TeacherController {

    private final TeacherServer teacherServer;
    private final StudentServer studentServer;
    private final StudentHomeworkServer studentHomeworkServer;
    private final HomeworkServer homeworkServer;
    @Autowired
    public TeacherController(TeacherServer teacherServer,StudentServer studentServer,StudentHomeworkServer studentHomeworkServer,HomeworkServer homeworkServer){
        this.teacherServer = teacherServer;
        this.studentServer = studentServer;
        this.studentHomeworkServer = studentHomeworkServer;
        this.homeworkServer = homeworkServer;
    }

    @RequestMapping("/addStudentOption")
    public String addStudentOption(){
//        System.out.println(username);
//        boolean flag = studentServer.addStudent(name,username,password);
        return "addStudent";
    }

    @RequestMapping("/addHomeworkOption")
    public String addHomeworkOption(){
//        System.out.println(username);
//        boolean flag = studentServer.addStudent(name,username,password);
        return "addHomework";
    }

    @RequestMapping("/queryHomeworkOption")
    public String queryHomeworkOption(){
//        System.out.println(username);
//        boolean flag = studentServer.addStudent(name,username,password);
        return "queryHomework";
    }


    @RequestMapping("/addStudent")
    @ResponseBody
    public DataResponse addStudent(String name, String username,String password){
//        System.out.println(username);
        boolean flag = studentServer.addStudent(name,username,password);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setFlag(flag);
        return dataResponse;
    }

    @RequestMapping("/addHomework")
    @ResponseBody
    public DataResponse addHomework(String title,String content){
        boolean flag = homeworkServer.addHomework(title,content);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setFlag(flag);
        return dataResponse;
    }

    @RequestMapping("/queryOneHomeworkSumbitJump")
    public String queryOneHomeworkSumbitJump(){

        return "queryStudentSumbitHomework";
    }


    @RequestMapping("/queryOneHomeworkSumbit")
    @ResponseBody
    public ListResponse<StudentHomework> queryOneHomeworkSumbit(String title){
        List<StudentHomework> list = studentHomeworkServer.SelectAllStudentHomeworkBytitle(title);
        ListResponse<StudentHomework> reList = new ListResponse<>();
        reList.setData(list);
        return reList;
    }

}
