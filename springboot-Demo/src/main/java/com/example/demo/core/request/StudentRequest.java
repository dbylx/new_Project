package com.example.demo.core.request;

import lombok.Data;

@Data
public class StudentRequest extends PageRequest {

    private String username;
    private String password;

}
