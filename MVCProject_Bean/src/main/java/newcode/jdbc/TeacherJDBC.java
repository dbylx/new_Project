package newcode.jdbc;

import newcode.Dao.TeacherDao;
import newcode.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class TeacherJDBC implements TeacherDao {
    private JDBCControll jdbcControll;
    @Autowired
    public TeacherJDBC(JDBCControll jdbcControll){
        this.jdbcControll = jdbcControll;
    }

    public ArrayList<StudentHomework> queryHomework(){
        ArrayList<StudentHomework> homeworkList = new ArrayList<StudentHomework>();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcControll.closeMysql();
        }
        return homeworkList;
    }


    public ArrayList<StudentHomework> queryDetailHomework(String title){
        ArrayList<StudentHomework> homeworkList = new ArrayList<StudentHomework>();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcControll.closeMysql();
        }
        return homeworkList;
    }


    public Boolean addHomework(String title,String content){
        try {
            String sqlString = "INSERT INTO school.s_homework(title, content) VALUES ('" + title + "','"  + content + "')";
            return jdbcControll.excuteUpdateSQL(sqlString);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            jdbcControll.closeMysql();
        }
    }

    public Boolean getTeacherIdentity(String teacherName,String passWord){
       try {
            String sqlString = "select * from s_teacher WHERE username = '"+teacherName+"'";

            jdbcControll.excuteSQL(sqlString);
            while(jdbcControll.getResultSet().next()) {

                if(jdbcControll.getResultSet().getString(4).equals(passWord)&&jdbcControll.getResultSet().getString(3).equals(teacherName)){
                    return true;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            jdbcControll.closeMysql();
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


