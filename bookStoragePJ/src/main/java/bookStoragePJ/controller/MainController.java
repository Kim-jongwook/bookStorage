package bookStoragePJ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "thymeleaf/main";
	}
	@RequestMapping("welcome")
	public String welcom() {
		return "thymeleaf/register/memberWelcome";
	}
}
