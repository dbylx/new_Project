package newcode.controller;

import newcode.entity.StudentHomework;
import newcode.jdbc.StudentJDBC;
import newcode.jdbc.TeacherJDBC;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TeacherJDBC.class);
        TeacherJDBC teacherJDBC = context.getBean("teacherJDBC", TeacherJDBC.class);

        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();

        req.setAttribute("homeWorkList",list);
        return "queryHomework";
    }

    @RequestMapping("/teacherChoose3")
    protected String Choosethree(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return "homework";
    }
}
