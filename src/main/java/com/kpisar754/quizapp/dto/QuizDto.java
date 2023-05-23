package com.kpisar754.quizapp.dto;

import com.kpisar754.quizapp.entity.enums.QuizResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class QuizDto {

    private UUID id;
    private List<QuestionDto> questionDtoList;
    @DateTimeFormat(pattern = "dd MMM yyyy, HH:mm:ss")
    private LocalDateTime quizStart;
    @DateTimeFormat(pattern = "dd MMM yyyy, HH:mm:ss")
    private LocalDateTime quizEnd;
    private QuizResult userResult;

    public QuizDto() {
        this.quizStart = LocalDateTime.now();
        this.userResult = QuizResult.NOT_CLASSIFIED;
    }
}
