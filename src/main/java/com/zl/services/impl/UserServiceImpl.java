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
    @Override
    public int insert(User user) {
       return userMapper.insert(user);
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
}
