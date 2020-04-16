package newcode.Dao;

public interface StudentDao {
    public boolean addStudent(String username, String password,String name);
    public boolean submitHomework(String username,String title,String content);
    public Boolean getStudentIdentity(String studentName,String passWord);
}
