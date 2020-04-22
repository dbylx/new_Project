package org.com.code.servers;

import org.com.code.model.StudentHomework;
import org.com.code.tool.JDBCControll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class TeacherJDBC{
    private JDBCControll jdbcControll;
    @Autowired
    public TeacherJDBC(JDBCControll jdbcControll){
        this.jdbcControll = jdbcControll;
    }

    public ArrayList<StudentHomework> queryHomework() throws SQLException {
        ArrayList<StudentHomework> homeworkList = new ArrayList<StudentHomework>();

//            jdbcControll = new JDBCControll();
            String sqlString = "select * from s_homework";

            jdbcControll.excuteSQL(sqlString);
            while(jdbcControll.getResultSet().next()) {
                StudentHomework homework = new StudentHomework();
                homework.setHomeworkTitle(jdbcControll.getResultSet().getString(1));
                homework.setHomeworkContent(jdbcControll.getResultSet().getString(3));
                homework.setCreateTime(jdbcControll.getResultSet().getString(2));
                homeworkList.add(homework);
            }

        return homeworkList;
    }


    public ArrayList<StudentHomework> queryDetailHomework(String title) throws SQLException {
        ArrayList<StudentHomework> homeworkList = new ArrayList<StudentHomework>();
//            jdbcControll = new JDBCControll();
        String sqlString = "select * from s_student_homework Where homework_title = '"+title+"'";

        jdbcControll.excuteSQL(sqlString);
        while(jdbcControll.getResultSet().next()) {
            StudentHomework homework = new StudentHomework();
            homework.setHomeworkTitle(jdbcControll.getResultSet().getString(2));
            homework.setHomeworkContent(jdbcControll.getResultSet().getString(3));
            homework.setCreateTime(jdbcControll.getResultSet().getString(4));
            homework.setUsername(jdbcControll.getResultSet().getString(1));
            homeworkList.add(homework);
        }

        return homeworkList;
    }


    public Boolean addHomework(String title,String content) throws SQLException {

        String sqlString = "INSERT INTO school.s_homework(title, content) VALUES ('" + title + "','"  + content + "')";
        boolean flag =  jdbcControll.excuteUpdateSQL(sqlString);

//        int i = 1/0;
//            if(title.equals("haohaoxuexi")){
//                System.out.println("enter");
//                throw new Exception("testTracstion");
//
        return flag;

    }

    public Boolean getTeacherIdentity(String teacherName,String passWord) throws SQLException {
        String sqlString = "select * from s_teacher WHERE username = '"+teacherName+"'";



        jdbcControll.excuteSQL(sqlString);
        while(jdbcControll.getResultSet().next()) {

            if(jdbcControll.getResultSet().getString(4).equals(passWord)&&jdbcControll.getResultSet().getString(3).equals(teacherName)){
                return true;
            }

        }




        return false;
    }

//    public static void main(String[] args){
//        TeacherJDBC teacherJDBC = new TeacherJDBC();
//        ArrayList<StudentHomework> homeworkList =teacherJDBC.queryDetailHomework("homework1");
//        for(int i = 0 ;i<homeworkList.size();i++){
//            s(homeworkList.get(i).getUsername());
//            s(homeworkList.get(i).getHomeworkTitle());
//            s(homeworkList.get(i).getHomeworkContent());
//            s(homeworkList.get(i).getCreateTime());
//        }
//
//    }
//
//    public static void s(Object s){
//        System.out.println(s);
//    }

}


