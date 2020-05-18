package com.example.demo.core.request;

import lombok.Data;

@Data
public class PageRequest {
    private int page = 0;
    private int size = 10;
}
