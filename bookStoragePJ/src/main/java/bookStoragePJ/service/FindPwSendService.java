package bookStoragePJ.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bookStoragePJ.domain.FindDTO;
import bookStoragePJ.mapper.FindMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class FindPwSendService {
	@Autowired
	EmailSendService emailSendService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	FindMapper findMapper;
	public void execute(HttpSession session, Model model) {
		FindDTO dto = (FindDTO) session.getAttribute("findDTO");
		
		String newPw = UUID.randomUUID().toString().substring(0, 16);
		dto.setMemPw(passwordEncoder.encode(newPw));
		findMapper.findPwSend(dto);
		model.addAttribute("memEmail", dto.getMemEmail());
		String html = "<html><body>"
				+ "<h2 style='font-weight: 700; font-size: 2rem; line-height: 2.75rem; padding-top: 64px; margin: 0;'>임시 비밀번호가 도착했습니다 :-)</h2><br>"
				+ "<p>발급받은 임시 비밀번호로 로그인하여 꼭 안전한 비밀번호로 변경해주세요.<br>"
				+ "임시 비밀번호는 " + newPw + "입니다.";
		String subject = "[북스토리지] 임시비밀번호 발급.";
		String fromEmail = "kimzong1smtp@gmail.com";
		String toEmail = dto.getMemEmail();
		emailSendService.mailsend(html, subject, fromEmail, toEmail);
	}
}
