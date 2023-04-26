package com.kpisar754.quizapp.repository;

import com.kpisar754.quizapp.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    Answer findByAnswerId(UUID id);
}
