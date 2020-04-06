package newcode.controller;

import org.springframework.context.annotation.ComponentScan;



import newcode.jdbc.JDBCControll;
import newcode.jdbc.StudentJDBC;
import newcode.jdbc.TeacherJDBC;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DealLoginController extends HttpServlet {
    @RequestMapping(value = "/dealLogin")
    public String DealLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("myname");
        System.out.println(new String(req.getParameter("myname") .getBytes("utf-8"),"utf-8"));
        String password =req.getParameter("mypwd");
        System.out.println(new String(req.getParameter("mypwd") .getBytes("iso8859-1"),"utf-8"));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TeacherJDBC.class);
        TeacherJDBC teacherJDBC = context.getBean("teacherJDBC", TeacherJDBC.class);

        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(StudentJDBC.class);
        StudentJDBC studentJDBC = context2.getBean("studentJDBC", StudentJDBC.class);

        if(teacherJDBC.getTeacherIdentity(userName,password)){
            System.out.println("teacher");
            req.getSession().setAttribute("username1",userName);
            return "teacherManage";

        }else if(studentJDBC.getStudentIdentity(userName,password)){
            System.out.println("student");
            req.getSession().setAttribute("username1",userName);

            return "studentManage";
        }else{

            return "login";
        }
    }
}
