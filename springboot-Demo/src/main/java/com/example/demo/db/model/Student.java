package com.example.demo.db.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "s_student")
public class Student {


    @Id
    private String username;
    private String name;
    private String password;

}
