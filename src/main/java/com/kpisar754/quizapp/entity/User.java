package com.kpisar754.quizapp.entity;

import com.kpisar754.quizapp.entity.enums.UserGender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID userId;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private UserGender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Quiz> userQuizzes = new ArrayList<>();

    public User(String nickname, String country, Integer age, UserGender gender) {
        this.nickname = nickname;
        this.country = country;
        this.age = age;
        this.gender = gender;
    }

    public User(String nickname, String country, Integer age, UserGender gender, List<Quiz> userQuizzes) {
        this.nickname = nickname;
        this.country = country;
        this.age = age;
        this.gender = gender;
        this.addQuizzes(userQuizzes);
    }

    public void addQuizzes(List<Quiz> quizzes) {
        quizzes.forEach(this::addQuiz);
    }

    public void addQuiz(Quiz quiz) {
        quiz.setUser(this);
        this.userQuizzes.add(quiz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(nickname, user.nickname) && Objects.equals(country, user.country) && Objects.equals(age, user.age) && gender == user.gender && Objects.equals(userQuizzes, user.userQuizzes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nickname, country, age, gender, userQuizzes);
    }
}
