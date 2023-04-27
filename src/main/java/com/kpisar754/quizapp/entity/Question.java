package com.kpisar754.quizapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID questionId;

    @Column(name = "question_content", unique = true, nullable = false)
    private String questionContent;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
    private Answer questionAnswer;

    @ManyToMany(mappedBy = "questions")
    @ToString.Exclude
    private List<Quiz> quizzes;

    public Question(String questionContent, Answer questionAnswer) {
        this.questionContent = questionContent;
        this.questionAnswer = questionAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId) && Objects.equals(questionContent, question.questionContent) && Objects.equals(questionAnswer, question.questionAnswer) && Objects.equals(quizzes, question.quizzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionContent, questionAnswer, quizzes);
    }
}
