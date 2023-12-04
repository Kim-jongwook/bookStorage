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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookStoragePJ.command.MemberCommand;
import bookStoragePJ.service.EmailCheckService;
import bookStoragePJ.service.IdCheckService;
import bookStoragePJ.service.MemberAutoNumService;
import bookStoragePJ.service.MemberEmailConfirmService;
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
	@Autowired
	IdCheckService idCheckService;
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	MemberEmailConfirmService memberEmailConfirmService;
	
	@RequestMapping("userAgree")
	public String agree() {
		return "thymeleaf/register/memberAgree";
	}
	
	@RequestMapping("userWrite")
	public String wirte(Model model) {
		autoNumService.execute(model);
		return "thymeleaf/register/memberForm";
	}
	
	@PostMapping("userIdCheck")
	public @ResponseBody String userIdCheck(@RequestParam(value = "userId") String userId) {
		String resultId = idCheckService.execute(userId);
		if (resultId == null) {
			return "사용가능한 아이디입니다.";
		} else {
			return "이미 사용중인 아이디입니다.";
		}
	}

	@PostMapping("userEmailCheck")
	public @ResponseBody String userEmailCheck(@RequestParam(value = "userEmail") String userEmail) {
		String resultEmail = emailCheckService.execute(userEmail);
		if (resultEmail == null) {
			return "사용가능한 이메일입니다.";
		} else {
			return "이미 사용중인 이메일입니다.";
		}
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
	
	@RequestMapping("memConfirm")
	public String memConfirm(@RequestParam(value = "chk") String chk) {
		int i = memberEmailConfirmService.execute(chk);
		if (i == 0) {
			return "thymeleaf/register/memberAlready";
		} else {
			return "thymeleaf/register/memberWelcome";
		}
	}
	
	@RequestMapping("already")
	public String welcom() {
		return "thymeleaf/register/memberAlready";
	}
	@RequestMapping("welcome")
	public String welcom1() {
		return "thymeleaf/register/memberWelcome";
	}
}
