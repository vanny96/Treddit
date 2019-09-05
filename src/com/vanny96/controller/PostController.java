package com.vanny96.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vanny96.model.Post;
import com.vanny96.model.User;
import com.vanny96.service.PostService;
import com.vanny96.service.UserService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"/", "/posts"})
	public String posts(Model model) {
		model.addAttribute("posts", postService.getAll());
		
		return "post/posts";
	}
	
	@GetMapping("posts/new")
	public String newPost(Model model) {
		model.addAttribute("post", new Post());
		return "post/postForm";
	}
	
	@PostMapping("/posts/new")
	public String savePost(@Valid Post post, BindingResult binding,
							Principal principal) {
		User user = userService.getByUsername(principal.getName());
		post.setOwner(user);
		
		if(binding.hasErrors()) {
			return "post/postForm";
		} else {
			postService.save(post);
			return "redirect:/posts";
		}
	}
	
	@GetMapping("posts/{id}")
	public String user(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getById(id));
		
		return "post/post";
	}
	
	@GetMapping("posts/{id}/delete")
	public String deleteUser(@PathVariable int id) {
		postService.delete(postService.getById(id));
		
		return "redirect:/posts";
	}
	
	@GetMapping("posts/{id}/edit")
	public String editUser(@PathVariable int id, Model model) {
		model.addAttribute("post", postService.getById(id));
		
		return "post/postForm";
	}
	
	@PostMapping("posts/{id}/edit")
	public String updateUser(@Valid Post post, BindingResult binding, Principal principal) {
		User user = userService.getByUsername(principal.getName());
		post.setOwner(user);
		
		if(binding.hasErrors()) {
			return "post/postForm";
		} else {
			postService.save(post);
			return "redirect:/posts";
		}
	}
}
