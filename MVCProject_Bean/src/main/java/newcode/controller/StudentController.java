package newcode.controller;

import newcode.model.StudentHomework;
import newcode.jdbc.StudentJDBC;
import newcode.jdbc.TeacherJDBC;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/student")
public class StudentController {
    private TeacherJDBC teacherJDBC;
    private StudentJDBC studentJDBC;

    @Autowired
    public StudentController(TeacherJDBC teacherJDBC, StudentJDBC studentJDBC) {
        this.teacherJDBC = teacherJDBC;
        this.studentJDBC = studentJDBC;
    }


    @RequestMapping("/choose")
    protected String choose1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("I do this");


        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();

        System.out.println("I do this");
        req.setAttribute("homeWorkList",list);
        return"queryHomework_student";
    }

    @RequestMapping("/dealSubmitHomework")
    public String submitHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<StudentHomework> list = teacherJDBC.queryHomework();
        String title = list.get(Integer.valueOf(req.getParameter("param"))).getHomeworkTitle();
        ArrayList<StudentHomework> detalList = teacherJDBC.queryDetailHomework(title);
        req.setAttribute("detalList",detalList);
        req.setAttribute("title",title);
        return "submitHomework";





    }


    @RequestMapping("/FinallyHomework")
    public void submitFinallHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


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
