package bookStoragePJ.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bookStoragePJ.command.MemberCommand;
import bookStoragePJ.domain.MemberDTO;
import bookStoragePJ.mapper.MemberMapper;

@Service
public class MemberWriteService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	EmailSendService emailSendService;

	public void execute(MemberCommand memberCommand, Model model) {
		MemberDTO dto = new MemberDTO();
		dto.setMemAddr(memberCommand.getMemAddr());
		dto.setMemAddrDetail(memberCommand.getMemAddrDetail());
		dto.setMemGender(memberCommand.getMemGender());
		dto.setMemId(memberCommand.getMemId());
		dto.setMemName(memberCommand.getMemName());
		dto.setMemNum(memberCommand.getMemNum());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemPost(memberCommand.getMemPost());
		dto.setMemPw(passwordEncoder.encode(memberCommand.getMemPw()));
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemBirth(memberCommand.getMemBirth());
		
		int i = memberMapper.memInsert(dto);
		model.addAttribute("memName", dto.getMemName());
		model.addAttribute("memEmail", dto.getMemEmail());
		
		if(i >= 1) {
			String html = "<html><body>"
					+ "<h2 style='font-weight: 700; font-size: 2rem; line-height: 2.75rem; padding-top: 64px; margin: 0;'>환영합니다 :-)</h2><br>"
					+ "<p>유일무이한 책 대여 서비스를 제공하고 있는 북스토리지입니다.<br>"
					+ "서비스 이용을 위해 이메일 인증이 필요하며, 아래의 버튼을 통해 <span style='color:blue;'>이메일을 인증</span>해주세요.<br><br>"
					+ "<a href='http://192.168.219.100:8080/register/memConfirm?chk=" + dto.getMemEmail() + "' style='display:inline-block; padding:20px; background-color:#005eb2; color:#fff; font-size:16px; text-align: center; text-decoration: none;'>이메일 인증</a>";
			String subject = "[북스토리지] 가입을 환영합니다.";
			String fromEmail = "kimzong1smtp@gmail.com";
			String toEmail = dto.getMemEmail();
			emailSendService.mailsend(html, subject, fromEmail, toEmail);
		}
	}
}
