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
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/teacherdo")
public class TeacherDoController {
    @RequestMapping("/dealAddStudent")
    public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(StudentJDBC.class);
        StudentJDBC studentJDBC = context2.getBean("studentJDBC", StudentJDBC.class);
        boolean flag = studentJDBC.addStudent(req.getParameter("usname"),req.getParameter("password"),req.getParameter("name"));
        PrintWriter out = resp.getWriter();
        if(flag){
            out.println("<script>");
            out.println("alert('Add successful！');");
            out.println("</script>");
        }else{
            out.println("<script>");
            out.println("alert('Add fail！');");
            out.println("</script>");
        }

    }


    @RequestMapping("/dealQueryHomework")
    public String dealQueryHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TeacherJDBC.class);
        TeacherJDBC teacherJDBC = context.getBean("teacherJDBC", TeacherJDBC.class);
        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();
        String title = list.get(Integer.valueOf(req.getParameter("param"))).getHomeworkTitle();
        ArrayList<StudentHomework> detalList = teacherJDBC.queryDetailHomework(title);
        req.setAttribute("detalList",detalList);
        return "queryDetailHomework";

    }

    @RequestMapping("/dealAddHomework")
    public void dealAddHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TeacherJDBC.class);
        TeacherJDBC teacherJDBC = context.getBean("teacherJDBC", TeacherJDBC.class);
        boolean flag = teacherJDBC.addHomework(req.getParameter("title"),req.getParameter("content"));
        PrintWriter out = resp.getWriter();
        if(flag){
            out.println("<script>");
            out.println("alert('Add successful！');");
            out.println("</script>");
        }else {
            out.println("<script>");
            out.println("alert('Add fail！');");
            out.println("</script>");
        }

    }
}
