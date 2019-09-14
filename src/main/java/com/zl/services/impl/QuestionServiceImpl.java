package com.zl.services.impl;

import com.zl.mapper.QuestionMapper;
import com.zl.model.Question;
import com.zl.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public int insert(Question question) {
        int retval = questionMapper.insert(question);
        return retval;
    }

    @Override
    public List<Question> list() {
        return questionMapper.list();
    }
}
