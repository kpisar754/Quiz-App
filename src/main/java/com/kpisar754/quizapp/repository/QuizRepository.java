package com.kpisar754.quizapp.repository;

import com.kpisar754.quizapp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    Quiz findByQuizId(UUID id);
}
