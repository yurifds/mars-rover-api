package com.mars.rover.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {

	@RequestMapping("/")
	public RedirectView home() {
		return new RedirectView("/swagger-ui.html");
	}
}
