package webcode.controller;

import webcode.jdbc.TeacherJDBC;
import webcode.model.StudentHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/teacher")
public class TeacherChooseController {
    @RequestMapping("/teacherChoose1")
    protected String Chooseone(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return "addStudent";
    }
    @RequestMapping("/teacherChoose2")
    protected String Choosetwo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TeacherJDBC teacherJDBC = new TeacherJDBC();
        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();

        req.setAttribute("homeWorkList",list);
        return "queryHomework";
    }

    @RequestMapping("/teacherChoose3")
    protected String Choosethree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return "homework";
    }
}
