package com.eshop.site;

import org.springframework.stereotype.Controller;

@Controller
public class MainController {
	public String viewHomePage() {
		return "index";
	}
}
