package bookStoragePJ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bookStoragePJ.command.LoginCommand;
import bookStoragePJ.service.MemLoginService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	MemLoginService memLoginService;
	
	@PostMapping("login")
	public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session) {
		memLoginService.execute(loginCommand, result, session);
		if(result.hasErrors()) {
			return "thymeleaf/main";
		}
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
