package bookStoragePJ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import bookStoragePJ.command.LoginCommand;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main(@ModelAttribute("loginCommand")LoginCommand loginCommand) {
		return "thymeleaf/main";
	}
}
