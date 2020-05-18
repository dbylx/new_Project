package com.example.demo.db.server;

import com.example.demo.db.mapper.HomeworkMapper;
import com.example.demo.db.model.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeworkServer {
    private HomeworkMapper homeWorkMapper;
    @Autowired
    public HomeworkServer(HomeworkMapper homeWorkMapper){
        this.homeWorkMapper = homeWorkMapper;
    }
    public List<Homework> SelectAllHomeworks(){
//        return null;
        return homeWorkMapper.SelectAllHomeworks();
    }


    public boolean addHomework(String title,String content){
        try {
            homeWorkMapper.addHomework(title, content);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
