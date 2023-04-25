package com.kpisar754.quizapp.entity;

import com.kpisar754.quizapp.entity.enums.QuizResult;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "quiz_info")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID quizId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "quiz_questions_jointable",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )
    @ToString.Exclude
    private List<Question> questions = new ArrayList<>();

    @ManyToOne
    private User user;

    @Column(name = "start")
    private LocalDateTime quizStart;

    @Column(name = "end")
    private LocalDateTime quizEnd;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_result", nullable = false)
    private QuizResult userResult;

    public Quiz(UUID quizId, List<Question> questions, User user, LocalDateTime quizStart, LocalDateTime quizEnd, QuizResult userResult) {
        this.quizId = quizId;
        this.addQuestions(questions);
        this.user = user;
        this.quizStart = quizStart;
        this.quizEnd = quizEnd;
        this.userResult = userResult;
    }

    public void addQuestions(List<Question> questions) {
        questions.forEach(this::addQuestion);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(quizId, quiz.quizId) && Objects.equals(questions, quiz.questions) && Objects.equals(user, quiz.user) && Objects.equals(quizStart, quiz.quizStart) && Objects.equals(quizEnd, quiz.quizEnd) && userResult == quiz.userResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, questions, user, quizStart, quizEnd, userResult);
    }
}
