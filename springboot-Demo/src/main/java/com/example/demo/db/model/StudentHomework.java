package com.example.demo.db.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_student_homework")
public class StudentHomework {
    @Id
    int id;
    String homework_title;
    String student_username;
    String homework_content;
    String comment;
    String create_time;
    float grade;

}
