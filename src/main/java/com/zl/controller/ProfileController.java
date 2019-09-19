package com.zl.controller;

import com.zl.dto.PaginationDTO;
import com.zl.model.User;
import com.zl.services.QuestionService;
import com.zl.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/{action}")
    public String index(@PathVariable("action") String action, Model model,
                        @RequestParam(value = "page",defaultValue ="1") Integer page,
                        @RequestParam(value = "size",defaultValue ="7") Integer size,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if(page<1){
            page=1;
        }
        if ("questions".equals(action)) {
            // 查询我的提问
            PaginationDTO list = questionService.list(user.getId(), page, size);
            model.addAttribute("paginationDTO",list);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        }
        if ("reply".equals(action)) {
            model.addAttribute("section", "reply");
            model.addAttribute("sectionName", "最新回复");
        }

        return "profile";
    }
}
