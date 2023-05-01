package com.kpisar754.quizapp.controller;

import com.kpisar754.quizapp.dto.QuestionDto;
import com.kpisar754.quizapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final QuestionService questionService;

    @GetMapping("/")
    public String getMainView(Model model) {
        model.addAttribute("message", "Welcome to Quiz !!!");
        return "/main";
    }

    @GetMapping("/admin")
    public String getAdminView(Model model) {
        model.addAttribute("adminMessage", "Admin Panel");
        return "/admin";
    }

    @GetMapping("/admin/add/question")
    public String getAddQuestionForm(Model model) {
        model.addAttribute("question", new QuestionDto());
        return "addQuestion";
    }

    @PostMapping("/admin/add/question")
    public String addQuestion(@ModelAttribute("question") QuestionDto questionDto) {
        questionService.saveQuestion(questionDto);
        return "redirect:/admin";
    }

    @GetMapping("/admin/see/allQuestions")
    public String getAllQuestions(Model model) {
        model.addAttribute("questions", questionService.findAllQuestions());
        return "seeAllQuestions";
    }
}
