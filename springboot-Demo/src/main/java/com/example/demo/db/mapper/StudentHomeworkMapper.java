package com.example.demo.db.mapper;

import com.example.demo.db.model.StudentHomework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentHomeworkMapper {
    List<StudentHomework> SelectAllStudentHomeworkBytitle(String homework_title);
    List<StudentHomework> SelectAllStudentHomeworkByUsername(String username);
    List<StudentHomework> SelectAllStudentHomeworkByUsernameTitle(String homework_title, String username);
    List<StudentHomework> SelectAllStudentHomeworkByGood();

    void InsertStudentHomework(@Param("username") String username, @Param("title")String title, @Param("content")String content);

    void UpdateReview(StudentHomework studentHomework);
    void UpdateHomework(StudentHomework studentHomework);
}
