package com.kpisar754.quizapp.service;

import com.kpisar754.quizapp.dto.QuestionDto;
import com.kpisar754.quizapp.entity.Answer;
import com.kpisar754.quizapp.entity.Question;
import com.kpisar754.quizapp.mapper.QuestionMapper;
import com.kpisar754.quizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final Random generator;

    public void saveQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionContent(questionDto.getContent());
        Answer answer = new Answer();
        answer.setAnswerOptionOne(questionDto.getAnswer().getAnswerOptionOne());
        answer.setAnswerOptionTwo(questionDto.getAnswer().getAnswerOptionTwo());
        answer.setAnswerOptionThree(questionDto.getAnswer().getAnswerOptionThree());
        answer.setAnswerOptionFour(questionDto.getAnswer().getAnswerOptionFour());
        answer.setIsOptionOneProper(questionDto.getAnswer().getIsOptionOneProper());
        answer.setIsOptionTwoProper(questionDto.getAnswer().getIsOptionTwoProper());
        answer.setIsOptionThreeProper(questionDto.getAnswer().getIsOptionThreeProper());
        answer.setIsOptionFourProper(questionDto.getAnswer().getIsOptionFourProper());

        answer.setQuestion(question);
        question.setQuestionAnswer(answer);
        questionRepository.save(question);
    }

    public List<QuestionDto> findAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::toDto)
                .toList();
    }

    public QuestionDto provideRandomQuestion() {
        List<QuestionDto> allQuestionDto = findAllQuestions();
        int index = generator.nextInt(allQuestionDto.size());
        return allQuestionDto.get(index);
    }

    public List<QuestionDto> provideRandomQuestionsForQuiz() {
        List<QuestionDto> quizQuestions = new ArrayList<>();
        while (quizQuestions.size() < 3) {
            QuestionDto randomQuestion = provideRandomQuestion();
            if (!quizQuestions.contains(randomQuestion)) {
                quizQuestions.add(randomQuestion);
            }
        }
        return quizQuestions;
    }

    public void deleteById(UUID id) {
        questionRepository.deleteById(id);
    }
}
