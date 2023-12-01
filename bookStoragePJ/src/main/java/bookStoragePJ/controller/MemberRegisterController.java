package bookStoragePJ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class MemberRegisterController {

	@RequestMapping("userAgree")
	public String agree() {
		return "thymeleaf/register/memberAgree";
	}
	
	@RequestMapping("userWrite")
	public String wirte() {
		return "thymeleaf/register/memberForm";
	}
}
