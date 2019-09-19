package com.zl.services.impl;

import com.zl.dto.AccessTokenDTO;
import com.zl.dto.GithubUser;
import com.zl.mapper.UserMapper;
import com.zl.model.User;
import com.zl.provider.GithubProvider;
import com.zl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.redirect_uri}")
    private String redirect_uri;
    @Value("${github.client.client_secret}")
    private String client_secret;

    /**
     * 添加用户
     * 1. 如果用户已登录则token
     *
     * @param user
     * @return
     */
    public int insert(User user) {
        //查询用户是否存在
        User userByAccountId = userMapper.findUserByAccountId(user.getAccountId());
        int retval = 0;
        if (userByAccountId != null) {
            retval = userMapper.updateUser(user);
        } else {
            retval = userMapper.insert(user); // 不存在则添加
        }
        return retval;
    }

    @Override
    public GithubUser getGithubUser(String code, String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        GithubUser githubUser = null;
        try {
            String access_token = githubProvider.getAccessToken(accessTokenDTO);
            githubUser = githubProvider.getUser(access_token);
            return githubUser;
        } catch (Exception e) {
            //token获取失败
        }
        return null;
    }

    @Override
    public User getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    user = getUserByToken(cookie.getValue());
                    if (user != null) {
                        return user;
                    }
                }

            }
        }
        return user;
    }

    @Override
    public User getUserByToken(String token) {
        return userMapper.findUserByToken(token);
    }

}
