package com.example.demo.db.mapper;

import com.example.demo.db.model.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    Teacher SeletOneTeacher(String username);
}
