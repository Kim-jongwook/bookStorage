package bookStoragePJ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bookStoragePJ.service.EmailCheckService;
import bookStoragePJ.service.IdCheckService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	IdCheckService idCheckService;
	@Autowired
	EmailCheckService emailCheckService;
	
	@PostMapping("userIdCheck")
	public @ResponseBody String userIdCheck(@RequestParam(value = "userId")String userId) {
		String resultId = idCheckService.execute(userId);
		if(resultId == null) {
			return "사용가능한 아이디입니다.";
		}else {
			return "이미 사용중인 아이디입니다.";
		}
	}
	
	@PostMapping("userEmailCheck")
	public @ResponseBody String userEmailCheck(@RequestParam(value = "userEmail")String userEmail) {
		String resultEmail = emailCheckService.execute(userEmail);
		if(resultEmail == null) {
			return "사용가능한 이메일입니다.";
		}else {
			return "이미 사용중인 이메일입니다.";
		}
	}
}
