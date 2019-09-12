package com.zl.controller;

import com.sun.deploy.net.HttpResponse;
import com.zl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	@GetMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		userService.getUserByToken( request,  response);
		return "index";
	}
}
