package com.zl.controller;

import com.sun.deploy.net.HttpResponse;
import com.zl.dto.PaginationDTO;
import com.zl.model.Question;
import com.zl.model.User;
import com.zl.services.QuestionService;
import com.zl.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model,
                        @RequestParam(value = "page",defaultValue ="1") Integer page,
                        @RequestParam(value = "size",defaultValue ="7") Integer size) {
        User userByToken = userService.getUserByToken(request, response);    //判断用户是否登录
        //查询数据
        PaginationDTO paginationDTO = questionService.list(page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "index";
    }
}
