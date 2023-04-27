package com.kpisar754.quizapp.dto;

import com.kpisar754.quizapp.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionDto {

    private UUID id;
    private String content;
    private Answer answer;
}
