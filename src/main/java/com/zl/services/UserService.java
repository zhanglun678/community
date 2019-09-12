package com.zl.services;

import com.zl.dto.GithubUser;
import com.zl.model.User;

public interface UserService {
    int insert(User user);
    GithubUser getGithubUser(String code,String state);

}
