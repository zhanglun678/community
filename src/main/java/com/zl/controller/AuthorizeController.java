package com.zl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${github.client.id}")
	private String client_id;
	@Value("${github.client.redirect_uri}")
	private String redirect_uri;
	@Value("${github.client.client_secret}")
	private String client_secret;

	@RequestMapping("/callback")
	public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri(redirect_uri);
		accessTokenDTO.setState(state);
		accessTokenDTO.setClient_id(client_id);
		accessTokenDTO.setClient_secret(client_secret);
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
