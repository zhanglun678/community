package com.zl.services;

import com.zl.dto.GithubUser;
import com.zl.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    int insert(User user);
    GithubUser getGithubUser(String code,String state);
    User getUserByToken(HttpServletRequest request,HttpServletResponse response);
    User getUserByToken(String token);
}
