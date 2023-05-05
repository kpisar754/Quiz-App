package com.kpisar754.quizapp.controller;

import com.kpisar754.quizapp.dto.QuestionDto;
import com.kpisar754.quizapp.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/quiz")
    public String getQuizView(Model model, HttpSession session) {
        List<QuestionDto> quizQuestions = questionService.provideRandomQuestionsForQuiz();
        session.setAttribute("quizSession", quizQuestions);
        model.addAttribute("questions", quizQuestions);
        return "/quizApp";
    }

    @PostMapping("/quiz")
    public String processQuizAnswers(@RequestParam Map<String, String> allAnswers, Model model, HttpSession session) {
        List<QuestionDto> quizQuestions = (List<QuestionDto>) session.getAttribute("quizSession");
        int score = questionService.calculateQuizResult(quizQuestions, allAnswers);
        model.addAttribute("quizQuestions", quizQuestions);
        model.addAttribute("score", score);
        return "/quizApp";
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

    @GetMapping("/delete-question/{id}")
    public String deleteQuestion(@PathVariable UUID id) {
        questionService.deleteById(id);
        return "redirect:/admin/see/allQuestions";
    }
}
