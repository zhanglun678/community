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
        int totalSize = questionMapper.count();
        int totalPage  = 0 ;
        //计算出总页码
        if(totalSize%size != 0 ){
            totalPage = (totalSize/size) + 1;
        }else if (totalSize%size == 0){
            totalPage = totalSize/size;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        Integer offset = (page-1) * size;
        PaginationDTO paginationDTO = new PaginationDTO();
        List<Question> questionList = questionMapper.list(offset, size);
        paginationDTO.setQuestionsList(questionList);   // 列表数据
        paginationDTO.getPage(page,size,totalSize,totalPage);
        return paginationDTO;
    }

    @Override
    public PaginationDTO list(Integer creator, Integer page, Integer size) {
        int totalSize = questionMapper.countByCreator(creator);
        int totalPage  = 0 ;
        //计算出总页码
        if(totalSize%size != 0 ){
            totalPage = (totalSize/size) + 1;
        }else if (totalSize%size == 0){
            totalPage = totalSize/size;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        Integer offset = (page-1) * size;
        PaginationDTO paginationDTO = new PaginationDTO();
        List<Question> questionList = questionMapper.listByCreator(creator,offset, size);
        paginationDTO.setQuestionsList(questionList);   // 列表数据
        paginationDTO.getPage(page,size,totalSize,totalPage);
        return paginationDTO;
    }
}
