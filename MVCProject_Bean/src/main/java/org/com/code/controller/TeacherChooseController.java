package org.com.code.controller;

import org.com.code.servers.StudentJDBC;
import org.com.code.model.StudentHomework;
import org.com.code.servers.TeacherJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/teacher")
public class TeacherChooseController {
    private TeacherJDBC teacherJDBC;
    private StudentJDBC studentJDBC;

    @Autowired
    public TeacherChooseController(TeacherJDBC teacherJDBC, StudentJDBC studentJDBC) {
        this.teacherJDBC = teacherJDBC;
        this.studentJDBC = studentJDBC;
    }


    @RequestMapping("/teacherChoose1")
    protected String Chooseone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return "addStudent";
    }
    @RequestMapping("/teacherChoose2")
    protected String Choosetwo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {


        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();

        req.setAttribute("homeWorkList",list);
        return "queryHomework";
    }

    @RequestMapping("/teacherChoose3")
    protected String Choosethree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return "homework";
    }
}
