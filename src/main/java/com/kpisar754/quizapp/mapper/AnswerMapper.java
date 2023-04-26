package com.kpisar754.quizapp.mapper;

import com.kpisar754.quizapp.dto.AnswerDto;
import com.kpisar754.quizapp.entity.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerMapper {

    public Answer fromDto(AnswerDto answerDto) {
        return Answer.builder()
                .answerId(answerDto.getId())
                .answerOption(answerDto.getOption())
                .build();
    }

    public AnswerDto toDto(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getAnswerId())
                .option(answer.getAnswerOption())
                .build();
    }
}
