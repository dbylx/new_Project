package webcode.controller;

import webcode.jdbc.StudentJDBC;
import webcode.jdbc.TeacherJDBC;
import webcode.model.StudentHomework;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
@RequestMapping("/student")
public class StudentController {
    @RequestMapping("/choose")
    protected String choose1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("I do this");
        TeacherJDBC teacherJDBC = new TeacherJDBC();
        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();

        System.out.println("I do this");
        req.setAttribute("homeWorkList",list);
        return"queryHomework_student";
    }

    @RequestMapping("/dealSubmitHomework")
    public String submitHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeacherJDBC teacherJDBC = new TeacherJDBC();
        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();
        String title = list.get(Integer.valueOf(req.getParameter("param"))).getHomeworkTitle();
        ArrayList<StudentHomework> detalList = teacherJDBC.queryDetailHomework(title);
        req.setAttribute("detalList",detalList);
        req.setAttribute("title",title);
        return "submitHomework";





    }


    @RequestMapping("/FinallyHomework")
    public void submitFinallHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentJDBC studentJDBC = new StudentJDBC();

        String username = (String) req.getSession().getAttribute("username1");
        String title = (String) req.getSession().getAttribute("title");
        String content = req.getParameter("content");
        PrintWriter out = resp.getWriter();
        if(studentJDBC.submitHomework(username,title,content)){
            out.println("<script>");
            out.println("alert('Add successful！');");
            out.println("</script>");
        }else{
            out.println("<script>");
            out.println("alert('Add fail！');");
            out.println("</script>");
        }






    }

}
