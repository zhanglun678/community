package com.zl.services;

import com.zl.model.Question;

import java.util.List;

public interface QuestionService {
    int insert(Question question);
    List<Question> list();
}
