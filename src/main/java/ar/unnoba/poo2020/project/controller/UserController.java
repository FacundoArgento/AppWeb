package ar.unnoba.poo2020.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.unnoba.poo2020.project.model.User;
import ar.unnoba.poo2020.project.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private IUserService userService;
	
	@Autowired
	public UserController(IUserService userService) { this.userService = userService; }
	
	@GetMapping("/new")
	public String userNew(Model model) {
		model.addAttribute( "user", new User());
		return "/users/new";
	}
	
	@PostMapping
	public String create(@ModelAttribute User user) {
		userService.create(user);
		return "redirect:/";
	}
}
