package com.kpisar754.quizapp.service;

import com.kpisar754.quizapp.dto.AnswerDto;
import com.kpisar754.quizapp.entity.Answer;
import com.kpisar754.quizapp.mapper.AnswerMapper;
import com.kpisar754.quizapp.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public Answer saveAnswer(AnswerDto answerDto) {
        return answerRepository.save(answerMapper.fromDto(answerDto));
    }

    public void deleteById(UUID id) {
        answerRepository.deleteById(id);
    }
}
