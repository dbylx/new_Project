package com.example.demo.db.mapper;

import com.example.demo.db.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> selectAll();

    Student SeletOneStudent(String username);
    void deleteStudent(String username);
    void InsertStudent(@Param("name") String name, @Param("username") String username, @Param("password") String password);


}