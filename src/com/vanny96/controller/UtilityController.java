package com.vanny96.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilityController {

	@GetMapping("/access-denied")
	public String accessDenied() {
		System.out.println("I'm here");
		return "exceptions/access-denied";
	}
}
