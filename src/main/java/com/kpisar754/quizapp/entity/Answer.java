package com.kpisar754.quizapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "answer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID answerId;

    @Column(name = "answer_option_one", unique = true, nullable = false)
    private String answerOptionOne;

    @Column(name = "answer_option_two", unique = true, nullable = false)
    private String answerOptionTwo;

    @Column(name = "answer_option_three", unique = true, nullable = false)
    private String answerOptionThree;

    @Column(name = "answer_option_four", unique = true, nullable = false)
    private String answerOptionFour;

    @Column(name = "is_proper_one", nullable = false)
    private Boolean isOptionOneProper;

    @Column(name = "is_proper_two", nullable = false)
    private Boolean isOptionTwoProper;

    @Column(name = "is_proper_three", nullable = false)
    private Boolean isOptionThreeProper;

    @Column(name = "is_proper_four", nullable = false)
    private Boolean isOptionFourProper;

    @OneToOne
    @JoinColumn(name = "question_id", unique = true)
    private Question question;

    public Answer(String answerOptionOne, String answerOptionTwo, String answerOptionThree, String answerOptionFour, Boolean isOptionOneProper, Boolean isOptionTwoProper, Boolean isOptionThreeProper, Boolean isOptionFourProper) {
        this.answerOptionOne = answerOptionOne;
        this.answerOptionTwo = answerOptionTwo;
        this.answerOptionThree = answerOptionThree;
        this.answerOptionFour = answerOptionFour;
        this.isOptionOneProper = isOptionOneProper;
        this.isOptionTwoProper = isOptionTwoProper;
        this.isOptionThreeProper = isOptionThreeProper;
        this.isOptionFourProper = isOptionFourProper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(answerId, answer.answerId) && Objects.equals(answerOptionOne, answer.answerOptionOne) && Objects.equals(answerOptionTwo, answer.answerOptionTwo) && Objects.equals(answerOptionThree, answer.answerOptionThree) && Objects.equals(answerOptionFour, answer.answerOptionFour) && Objects.equals(isOptionOneProper, answer.isOptionOneProper) && Objects.equals(isOptionTwoProper, answer.isOptionTwoProper) && Objects.equals(isOptionThreeProper, answer.isOptionThreeProper) && Objects.equals(isOptionFourProper, answer.isOptionFourProper) && Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, answerOptionOne, answerOptionTwo, answerOptionThree, answerOptionFour, isOptionOneProper, isOptionTwoProper, isOptionThreeProper, isOptionFourProper, question);
    }
}
