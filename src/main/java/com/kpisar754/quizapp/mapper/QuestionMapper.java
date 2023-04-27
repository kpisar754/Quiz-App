package com.kpisar754.quizapp.mapper;

import com.kpisar754.quizapp.dto.QuestionDto;
import com.kpisar754.quizapp.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionMapper {

    public Question fromDto(QuestionDto questionDto) {
        return Question.builder()
                .questionId(questionDto.getId())
                .questionContent(questionDto.getContent())
                .questionAnswer(questionDto.getAnswer())
                .build();
    }

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .id(question.getQuestionId())
                .content(question.getQuestionContent())
                .answer(question.getQuestionAnswer())
                .build();
    }
}
