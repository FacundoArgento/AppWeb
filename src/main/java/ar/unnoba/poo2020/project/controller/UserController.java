package ar.unnoba.poo2020.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public String userNew(Model model, Authentication auth) {
		model.addAttribute( "user", new User());
		if(auth != null){
	        User sesionUser = (User) auth.getPrincipal();
	        model.addAttribute("name", sesionUser.getFirstName());
            model.addAttribute("lastName", sesionUser.getLastName());
		}
		return "/users/new";
	}
	
	@PostMapping
	public String create(@ModelAttribute User user, Model model, Authentication auth) {
		if(auth != null){
	        User sesionUser = (User) auth.getPrincipal();
	        model.addAttribute("name", sesionUser.getFirstName());
            model.addAttribute("lastName", sesionUser.getLastName());
		}
		
		userService.create(user);
		return "redirect:/users";
	}
}
