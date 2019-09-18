package com.zl.services.impl;

import com.zl.dto.PaginationDTO;
import com.zl.mapper.QuestionMapper;
import com.zl.model.Question;
import com.zl.services.QuestionService;
import javafx.scene.control.Pagination;
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
    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = (page-1) * size;
        PaginationDTO paginationDTO = new PaginationDTO();
        List<Question> questionList = questionMapper.list(offset, size);
        int totalSize = questionMapper.count();
        paginationDTO.setQuestionsList(questionList);   // 列表数据
        paginationDTO.getPage(page,size,totalSize);
        return paginationDTO;
    }
}
