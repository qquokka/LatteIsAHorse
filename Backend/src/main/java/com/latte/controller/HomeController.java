package com.latte.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController implements ErrorController {
	@GetMapping({ "/", "/error" })
	public String index() {
		return "index.html";
//		return null;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
