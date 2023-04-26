package com.kpisar754.quizapp.controller;

import com.kpisar754.quizapp.dto.AnswerDto;
import com.kpisar754.quizapp.dto.QuestionDto;
import com.kpisar754.quizapp.entity.Answer;
import com.kpisar754.quizapp.entity.Question;
import com.kpisar754.quizapp.service.AnswerService;
import com.kpisar754.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @GetMapping("/")
    public String getMainView(Model model) {
        model.addAttribute("message", "Welcome to Quiz !!!");
        return "/main";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("question", new QuestionDto());
        model.addAttribute("answer", new AnswerDto());
        return "/add";
    }

    @PostMapping
    public String addQuestion(@ModelAttribute QuestionDto questionDto, AnswerDto answerDto) {
        Answer answer = answerService.saveAnswer(answerDto);
        List<Answer> answers = new LinkedList<>();
        answers.add(answer);
        Question question = questionService.saveQuestion(questionDto);
        question.addAnswers(answers);

        return "redirect:/main";
    }
}
