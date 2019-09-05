package com.vanny96.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vanny96.model.User;
import com.vanny96.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("")
	public String users(Model model) {
		model.addAttribute("users", userService.getAll());
		return "user/users";
	}
	
	@GetMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "user/userForm";
	}
	
	@PostMapping("")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult binding) {
		
		System.out.println("Binding "+user.getUsername());
		
		if(binding.hasErrors()) {
			return "user/userForm";
		} else {
			userService.save(user);
			return "redirect:/users";
		}
	}
	
	@GetMapping("/{id}")
	public String user(@PathVariable int id, Model model) {
		model.addAttribute("user", userService.getById(id));
		
		return "user/user";
	}
	
	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable int id) {
		userService.delete(userService.getById(id));
		
		return "redirect:/users";
	}
	
	@GetMapping("/{id}/edit")
	public String editPassword() {
		return "user/passwordForm";
	}
	
	@PostMapping("/{id}/edit")
	public String updatePassword(@RequestParam String oldPassword,
								 @RequestParam String newPassword,
								 Principal principal) {
		User user = userService.getByUsername(principal.getName());
		
		if(encoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(newPassword);
			userService.update(user);
			
			return "redirect:/login";
		} else {
			return "redirect:/users/"+user.getId()+"/edit?error";
		}
	}
}
