package bookStoragePJ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bookStoragePJ.service.EmailCheckService;
import bookStoragePJ.service.IdCheckService;
import bookStoragePJ.service.MemberEmailConfirmService;

@RestController
@RequestMapping("check")
public class CheckRestController {
	@Autowired
	IdCheckService idCheckService;
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	MemberEmailConfirmService memberEmailConfirmService;

	@PostMapping("userIdCheck")
	public String userIdCheck(@RequestParam(value = "userId") String userId) {
		String resultId = idCheckService.execute(userId);
		if (resultId == null) {
			return "사용가능한 아이디입니다.";
		} else {
			return "이미 사용중인 아이디입니다.";
		}
	}

	@PostMapping("userEmailCheck")
	public String userEmailCheck(@RequestParam(value = "userEmail") String userEmail) {
		String resultEmail = emailCheckService.execute(userEmail);
		if (resultEmail == null) {
			return "사용가능한 이메일입니다.";
		} else {
			return "이미 사용중인 이메일입니다.";
		}
	}

	@RequestMapping("memConfirm")
	public String memConfirm(@RequestParam(value = "chk") String chk) {
		int i = memberEmailConfirmService.execute(chk);
		if (i == 0) {
			return "이미 인증되어 있습니다.";
		} else {
			return "thymeleaf/register/memberWelcome";
		}
	}

}
