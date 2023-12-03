package com.quizapp.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.quizservice.model.QuestionWrapper;
import com.quizapp.quizservice.model.ResponseModel;
import com.quizapp.quizservice.services.QuizService;



@RestController

@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("create")
    public ResponseEntity<String> addQuiz(@RequestParam String category, @RequestParam int num, @RequestParam String title){
        
        return this.quizService.addQuiz(category, num, title);
        
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> fetchQuestionForQuiz(@PathVariable Long id){
        return this.quizService.fetchQuestionsByQuizId(id);
    } 
    
    
    @PostMapping("submitquiz")
    public ResponseEntity<Integer> calculateResult( @RequestBody List<ResponseModel> responses){
        return this.quizService.calculateResult(responses);
    }
}
