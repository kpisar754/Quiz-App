package com.kpisar754.quizapp.entity;

import com.kpisar754.quizapp.entity.enums.ProperAnswerIndex;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Answer> answers = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "proper_answer", nullable = false)
    private ProperAnswerIndex properAnswer;

    @ManyToMany(mappedBy = "questions")
    @ToString.Exclude
    private List<Quiz> quizzes;

    public Question(UUID questionId, String questionContent, List<Answer> answers, ProperAnswerIndex properAnswer) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.addAnswers(answers);
        this.properAnswer = properAnswer;
    }

    public void addAnswers(List<Answer> answers) {
        answers.forEach(this::addAnswer);
    }

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        this.answers.add(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId) && Objects.equals(questionContent, question.questionContent) && Objects.equals(answers, question.answers) && Objects.equals(properAnswer, question.properAnswer) && Objects.equals(quizzes, question.quizzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionContent, answers, properAnswer, quizzes);
    }
}
