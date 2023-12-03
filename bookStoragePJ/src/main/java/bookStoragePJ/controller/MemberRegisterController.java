package bookStoragePJ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bookStoragePJ.command.MemberCommand;
import bookStoragePJ.service.MemberAutoNumService;
import bookStoragePJ.service.MemberWriteService;

@Controller
@RequestMapping("register")
public class MemberRegisterController {
	@ModelAttribute
	public MemberCommand memberCommand() {
		return new MemberCommand();
	}
	@Autowired
	MemberAutoNumService autoNumService;
	@Autowired
	MemberWriteService memberWriteService;
	
	@RequestMapping("userAgree")
	public String agree() {
		return "thymeleaf/register/memberAgree";
	}
	
	@RequestMapping("userWrite")
	public String wirte(Model model) {
		autoNumService.execute(model);
		return "thymeleaf/register/memberForm";
	}
	
	@RequestMapping(value = "userRegist", method = RequestMethod.POST)
	public String userRegist(@Validated MemberCommand memberCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/register/memberForm";
		}
		if(!memberCommand.isMemPwEqualsMemPwCon()) {
			result.rejectValue("memPwCon", "memberCommand.memPwCon", "비밀번호가 서로 일치하지 않습니다.");
			return "thymeleaf/register/memberForm";
		}
		
		memberWriteService.execute(memberCommand, model);
		return "thymeleaf/register/memberEmailSend";
	}
}
