package bookStoragePJ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bookStoragePJ.command.FindIdCommand;
import bookStoragePJ.command.FindPwCommand;
import bookStoragePJ.command.FindPwUpdateCommand;
import bookStoragePJ.domain.FindDTO;
import bookStoragePJ.service.FindIdService;
import bookStoragePJ.service.FindPwSendService;
import bookStoragePJ.service.FindPwService;
import bookStoragePJ.service.FindPwUpdateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("help")
public class HelpController {

	@Autowired
	FindIdService findIdService;
	@Autowired
	FindPwService findPwService;
	@Autowired
	FindPwUpdateService findPwUpdateService;
	@Autowired
	FindPwSendService findPwSendService;
	
	
	@GetMapping("findId")
	public String findId(Model model) {	
		model.addAttribute("findIdCommand", new FindIdCommand());
		return "thymeleaf/help/findId";
	}
	
	@PostMapping("findId")
	public String findId(@Validated FindIdCommand findIdCommand,
			BindingResult result, Model model) {
		findIdService.execute(findIdCommand, model);
		if(result.hasErrors()) {
			return "thymeleaf/help/findId";
		}
		return "thymeleaf/help/findIdOk";
	}
	
	@GetMapping("findPw")
	public String findPw(Model model) {
		model.addAttribute("findPwCommand", new FindPwCommand());
		return "thymeleaf/help/findPw";
	}
	
	@PostMapping("findPw")
	public String findPw(@Validated FindPwCommand findPwCommand, BindingResult result, Model model, HttpSession session) {
		findPwService.execute(findPwCommand, result, model, session);
		if(result.hasErrors()) {
			return "thymeleaf/help/findPw";
		}else {
			return "thymeleaf/help/findPwOk";
		}
	}
	
	@GetMapping("findPwUpdate")
	public String findPwUpdate(Model model, HttpSession session) {
		model.addAttribute("findPwUpdateCommand", new FindPwUpdateCommand());
		return "thymeleaf/help/findPwUpdate";
	}
	
	@PostMapping("findPwUpdate")
	public String findPwUpdate(@Validated FindPwUpdateCommand findPwUpdateCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/help/findPwUpdate";
		}
		findPwUpdateService.execute(findPwUpdateCommand, model);
		session.invalidate();
		return "thymeleaf/help/findPwUpdateOk";
	}
	
	@GetMapping("findPwSend")
	public String findPwSend(HttpSession session,Model model) {
		findPwSendService.execute(session, model);
		session.invalidate();
		return "thymeleaf/help/findPwSendOk";
	}
}
