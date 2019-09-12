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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletRequest request, HttpServletResponse response) {
		GithubUser githubUser = userService.getGithubUser(code, state);
		if(githubUser!=null) {
			User user = new User();
			user.setAccountId(String.valueOf(githubUser.getId()));
			user.setName(String.valueOf(githubUser.getName()));
			String token = UUID.randomUUID().toString();
			user.setToken(token);
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(System.currentTimeMillis());
			userService.insert(user);
			//写入COOKIE
			response.addCookie(new Cookie("token",token));
			return "redirect:/";
		}else{
			//登录失败
			return "redirect:/";
		}
	}

	@GetMapping("/call")
	public String test() {
		return "index";
	}
}
