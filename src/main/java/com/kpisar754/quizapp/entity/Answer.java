package com.kpisar754.quizapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "answer")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID answerId;

    @Column(name = "answer_option", unique = true, nullable = false)
    private String answerOption;

    @ManyToOne
    private Question question;

    public Answer(UUID answerId, String answerOption, Question question) {
        this.answerId = answerId;
        this.answerOption = answerOption;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(answerId, answer.answerId) && Objects.equals(answerOption, answer.answerOption) && Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, answerOption, question);
    }
}
