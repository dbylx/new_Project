package com.example.demo.controller;

import com.example.demo.core.request.LoginRequest;
import com.example.demo.core.request.StudentHomeworkRequest;
import com.example.demo.core.request.StudentRequest;
import com.example.demo.core.request.TeacherRequest;
import com.example.demo.core.response.DataResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.db.model.Homework;
import com.example.demo.db.model.Student;
import com.example.demo.db.model.StudentHomework;
import com.example.demo.db.model.Teacher;
import com.example.demo.db.server.HomeworkServer;
import com.example.demo.db.server.StudentHomeworkServer;
import com.example.demo.db.server.StudentServer;
import com.example.demo.db.server.TeacherServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class APIController {
//

    private final StudentServer studentServer;
    private final HomeworkServer homeWorkServer;
    private final StudentHomeworkServer studentHomeworkServer;
    private final TeacherServer teacherServer;

    @Autowired
    public APIController(StudentServer studentServer, HomeworkServer homeWorkServer,StudentHomeworkServer studentHomeworkServer,TeacherServer teacherServer) {
        this.studentServer = studentServer;
        this.homeWorkServer = homeWorkServer;
        this.studentHomeworkServer = studentHomeworkServer;
        this.teacherServer = teacherServer;
    }


    @RequestMapping("/login")
    public String Login(){
        return "login";
    }

    @RequestMapping("/dealLogin")
    public String DealLogin(LoginRequest loginRequest, HttpServletResponse response) {
//        System.out.println(loginRequest.getPassword());
//        System.out.println(loginRequest.getUsername());
        Boolean isS = studentServer.isLogin(loginRequest.getUsername(),loginRequest.getPassword());
        if(isS){
            Cookie cookie = new Cookie("username",loginRequest.getUsername());
            response.addCookie(cookie);
            return "studentManage";
        }
        Boolean isT = teacherServer.isLogin(loginRequest.getUsername(),loginRequest.getPassword());
        if(isT){
            Cookie cookie = new Cookie("username",loginRequest.getUsername());
            response.addCookie(cookie);
            return "teacherManage";
        }


        return "login";
    }

    @RequestMapping("/homeworkList")
    @ResponseBody
    public ListResponse<Homework> getHomeworkList(){
        ListResponse<Homework> listResponse = new ListResponse<>();
        listResponse.setData(homeWorkServer.SelectAllHomeworks());
        return listResponse;
    }

















    @RequestMapping("/student_login")
    public Boolean isLogin(@RequestBody StudentRequest studentRequest){
        Boolean isY = studentServer.isLogin(studentRequest.getUsername(),studentRequest.getPassword());
        DataResponse<Student> dataResponse = new DataResponse<>();
        System.out.println(isY);
        return isY;
    }

    @RequestMapping("/teacher_login")
    public Boolean isLogin(@RequestBody TeacherRequest teacherRequest){
        Boolean isY = teacherServer.isLogin(teacherRequest.getUsername(),teacherRequest.getPassword());
        DataResponse<Teacher> dataResponse = new DataResponse<>();
        System.out.println(isY);
        return isY;
    }


    @RequestMapping("/studentHomeworkList")
    public ListResponse<StudentHomework> getStudentHomeworkList(@RequestBody StudentHomeworkRequest studentHomeworkRequest){
        ListResponse<StudentHomework> listResponse = new ListResponse<>();
        listResponse.setData(studentHomeworkServer.SelectAllStudentHomeworkBytitle(studentHomeworkRequest.getTitle()));
        return listResponse;
    }

//    @RequestMapping("/dealLogin")
////    @ResponseBody




    //    @RequestMapping("/student")
//
//    public ListResponse<Student> getObject(){
//        ListResponse<Student> response = new ListResponse<>();
//        List<Student> list = studentServer.findAll();
//        response.setData(list);
//        return response;
//    }
    /*@RequestMapping("/studentpage")
    public PageResponse<Student> Login(@RequestBody StudentRequest studentRequest){
        int page = studentRequest.getPage();
        int size = studentRequest.getSize();
        Page<Student> list =  studentServer.findAll(page,size);
        PageResponse<Student> pageResponse = new PageResponse<>();
        pageResponse.setData(list.getContent());
        pageResponse.setTotal(list.getTotalElements());
        return pageResponse;
    }*/

  /*  @RequestMapping("/studentpage1")
    public PageResponse<Student> Login1(@RequestBody StudentRequest studentRequest){
        Student student = new Student();
        student.setUsername(studentRequest.getUsername());
        Page<Student> list = studentServer.findAll(student,studentRequest.getPage(),studentRequest.getSize());
        PageResponse<Student> pageResponse = new PageResponse<>();
        pageResponse.setData(list.getContent());
        pageResponse.setTotal(list.getTotalElements());
        return pageResponse;
    }*/
//    @RequestMapping("/testMapper")
//    public ListResponse<Student> test(){
//        List<Student> list = studentServer.findAllStudent();
//        ListResponse<Student> listResponse = new ListResponse<>();
//        listResponse.setData(list);
//        return listResponse;
//    }



}
