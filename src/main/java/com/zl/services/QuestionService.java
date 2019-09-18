package com.zl.services;

import com.zl.dto.PaginationDTO;
import com.zl.model.Question;


public interface QuestionService {
    int insert(Question question);
    PaginationDTO list(Integer page , Integer Size);
}
