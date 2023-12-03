package com.quizapp.quizservice.feign;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizapp.quizservice.model.QuestionWrapper;
import com.quizapp.quizservice.model.ResponseModel;

@FeignClient("QUESTION-SERVICE")
public interface QuizInteface {

    @PostMapping("question/generate")
    public ResponseEntity<List<Long>> generateQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer num);
      

    @PostMapping("question/get-questions-for-quiz")
    public ResponseEntity<List<QuestionWrapper>> fetchQuestionsByIds(@RequestBody List<Long> questionIds );

    @PostMapping("question/getscore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<ResponseModel> responses);
    
}
