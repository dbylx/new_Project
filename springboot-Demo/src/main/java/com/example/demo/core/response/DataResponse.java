package com.example.demo.core.response;

import lombok.Data;

@Data
public class DataResponse<T> extends BaseResponse {
    private T data;
}
