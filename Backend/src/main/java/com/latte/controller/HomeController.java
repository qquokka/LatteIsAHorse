package com.latte.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/v1")
public class HomeController implements ErrorController{
	@GetMapping("/")
	public String index() {
		return "index.html";
	}

	@GetMapping("/error")
	public String redirect() {
		return "index.html";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
