package com.example.demo.core.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class ListResponse<T> extends BaseResponse {
    List<T> data;
}
