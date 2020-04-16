package newcode.Dao;

import newcode.model.StudentHomework;

import java.util.ArrayList;

public interface TeacherDao {
    public ArrayList<StudentHomework> queryHomework();
    public ArrayList<StudentHomework> queryDetailHomework(String title);
    public Boolean addHomework(String title,String content);
    public Boolean getTeacherIdentity(String teacherName,String passWord);

}
