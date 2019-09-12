package com.zl.controller;

import com.zl.dto.AccessTokenDTO;
import com.zl.dto.GithubUser;
import com.zl.mapper.UserMapper;
import com.zl.model.User;
import com.zl.provider.GithubProvider;
import com.zl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 
 * @author Administrator
 * @todo 授权登录
 */
@Controller
public class AuthorizeController {


	@Autowired
	private UserService userService;


	@RequestMapping("/callback")
	public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletRequest request) {
		GithubUser githubUser = userService.getGithubUser(code, state);
		if(githubUser!=null) {
			User user = new User();
			user.setAccountId(String.valueOf(githubUser.getId()));
			user.setName(String.valueOf(githubUser.getName()));
			user.setToken(UUID.randomUUID().toString());
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(System.currentTimeMillis());
			userService.insert(user);
			request.getSession().setAttribute("user", githubUser);
			return "index";
		}else{
			//登录失败
			return "index";
		}
	}

	@GetMapping("/call")
	public String test() {
		return "index";
	}
}
