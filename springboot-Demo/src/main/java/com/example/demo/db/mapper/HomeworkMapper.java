package com.example.demo.db.mapper;


import com.example.demo.db.model.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeworkMapper {
    List<Homework> SelectAllHomeworks();
    void addHomework(@Param("title")String title, @Param("content") String content);
}
