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

    @RequestMapping("/studentReviewHomework")
    public String reviewHomework(){

        return "studentReviewHomework";
    }

    @RequestMapping("/goodHomeworkDetail")
    public String goodHomeworkDetail(){

        return "goodHomeworkDetail";
    }



    @RequestMapping("/viewHomework")
    public String viewHomework(){
        return "studentQueryHomework";
    }

    @RequestMapping("/viewMyHomework")
    public String viewMyHomework(){
        return "queryMyHomework";
    }

    @RequestMapping("/submitHomeworkJump")
    public String submitHomeworkJump(){
        return "submitHomework";
    }

    @RequestMapping("/viewGoodHomework")
    public String viewGoodHomeworkJump(){
        return "viewGoodHomework";
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

    @ResponseBody
    @RequestMapping("/queryMyHomeworkSumbit")
    public ListResponse<StudentHomework> queryMyHomework(String username) {
      ListResponse<StudentHomework> listResponse = new ListResponse<>();
      listResponse.setData(studentHomeworkServer.SelectAllStudentHomeworkByUsername(username));
      return listResponse;
    }



    @ResponseBody
    @RequestMapping("/queryGoodHomeworkSumbit")
    public ListResponse<StudentHomework> queryGoodHomeworkSumbit() {
        ListResponse<StudentHomework> listResponse = new ListResponse<>();
        listResponse.setData(studentHomeworkServer.SelectAllStudentHomeworkByGood());
        return listResponse;
    }




    @ResponseBody
    @RequestMapping("/updateHomework")
    public DataResponse updateHomework(StudentHomework studentHomework){
        System.out.println(studentHomework.toString());
        DataResponse dataResponse = new DataResponse();
        List<StudentHomework> list = studentHomeworkServer.SelectAllStudentHomeworkByUsernameTitle(studentHomework.getHomework_title(),studentHomework.getStudent_username());
        if(list.get(0).getGrade()!=-1){
            dataResponse.setFlag(false);
            return dataResponse;
        }
        boolean flag = studentHomeworkServer.UpdateHomework(studentHomework);



        dataResponse.setFlag(true);
        return dataResponse;
    }

}
