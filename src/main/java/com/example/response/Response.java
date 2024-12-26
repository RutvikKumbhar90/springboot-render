package com.example.response;


import lombok.Data;

import java.util.List;

@Data
public class Response<T> {
    private List<T> allStudents;
    private int skip;
    private int limit;
    private int total;

}
