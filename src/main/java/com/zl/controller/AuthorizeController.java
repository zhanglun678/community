package com.zl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zl.dto.AccessTokenDTO;
import com.zl.dto.GithubUser;
import com.zl.provider.GithubProvider;

/**
 * 
 * @author Administrator
 * @todo 授权登录
 */
@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;

	@RequestMapping("/callback")
	public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
		accessTokenDTO.setState(state);
		accessTokenDTO.setClient_id("688182fe5882ee89f01c");
		accessTokenDTO.setClient_secret("7e11ec36a2e7f0ae42fb1c814da0f0338746bda4");
		String access_token = githubProvider.getAccessToken(accessTokenDTO);
		GithubUser user = githubProvider.getUser(access_token);
		System.out.println(user.getId());
		System.out.println(user.getName());
		return "index";
	}

	@GetMapping("/call")
	public String test() {
		return "index";
	}
}
