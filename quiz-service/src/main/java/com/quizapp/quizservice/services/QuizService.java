package com.quizapp.quizservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.quizservice.feign.QuizInteface;
import com.quizapp.quizservice.model.QuestionWrapper;
import com.quizapp.quizservice.model.ResponseModel;
import com.quizapp.quizservice.repository.QuizRepository;
import com.quizapp.quizservice.entity.QuizEntity;





@Service
public class QuizService {
  
    @Autowired
     private QuizRepository quizRepository;
    @Autowired
     private  QuizInteface quizInterface;



    public ResponseEntity<String> addQuiz(String category, int num, String title) {
        
        List<Long> questions = this.quizInterface.generateQuestionForQuiz(category,num).getBody();

        QuizEntity  quiz = new QuizEntity();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> fetchQuestionsByQuizId(Long quizId){

        Optional<QuizEntity> quiz = this.quizRepository.findById(quizId);

        List<Long> quetionIds = quiz.isPresent() ? quiz.get().getQuestionIds() : new ArrayList<>();



        List<QuestionWrapper> questionForUser = this.quizInterface.fetchQuestionsByIds(quetionIds).getBody();

            return new ResponseEntity<>(questionForUser, HttpStatus.OK);        

    }

    public ResponseEntity<Integer> calculateResult( List<ResponseModel> responses){

          Integer score = this.quizInterface.calculateScore(responses).getBody();
        return new ResponseEntity<>(score, HttpStatus.OK);

    }

    



    
}
