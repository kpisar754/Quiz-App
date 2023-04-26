package com.kpisar754.quizapp.mapper;

import com.kpisar754.quizapp.dto.QuestionDto;
import com.kpisar754.quizapp.entity.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionMapper {

    public Question fromDto(QuestionDto questionDto) {
        return Question.builder()
                .questionId(questionDto.getId())
                .questionContent(questionDto.getContent())
                .answers(questionDto.getAnswers())
                .properAnswer(questionDto.getProper())
                .build();
    }

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .id(question.getQuestionId())
                .content(question.getQuestionContent())
                .answers(question.getAnswers())
                .proper(question.getProperAnswer())
                .build();
    }
}
