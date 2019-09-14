package com.zl.controller;

import com.zl.model.Question;
import com.zl.model.User;
import com.zl.services.impl.QuestionServiceImpl;
import com.zl.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController {
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) {
        User user = userService.getUserByToken(request, response);   //判断用户是否登录
        if(user != null) {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setLikeCount(0);
            question.setViewCount(0);
            int insert = questionService.insert(question);
            if (insert > 0) {
                return "redirect:/";
            }
        }else{
            model.addAttribute("errorMsg","用户未登录");
            return "publish";
        }
        return "publish";
    }
}