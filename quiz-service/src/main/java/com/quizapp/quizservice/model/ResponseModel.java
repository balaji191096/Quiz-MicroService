package com.quizapp.quizservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseModel {

    private Long id;
    private String answer;
    
}
