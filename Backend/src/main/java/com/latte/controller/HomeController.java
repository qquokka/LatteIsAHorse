package com.latte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class HomeController {
	@GetMapping("/")
	public String index() {
//		return "index.html";
		return null;
	}
}
