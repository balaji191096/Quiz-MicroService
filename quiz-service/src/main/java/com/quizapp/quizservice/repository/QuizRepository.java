package com.quizapp.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizapp.quizservice.entity.QuizEntity;


public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    
} 