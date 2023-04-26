package com.kpisar754.quizapp.service;

import com.kpisar754.quizapp.dto.QuestionDto;
import com.kpisar754.quizapp.entity.Question;
import com.kpisar754.quizapp.mapper.QuestionMapper;
import com.kpisar754.quizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public Question saveQuestion(QuestionDto questionDto) {
        return questionRepository.save(questionMapper.fromDto(questionDto));
    }

    public void deleteById(UUID id) {
        questionRepository.deleteById(id);
    }
}
