package bookStoragePJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import bookStoragePJ.command.LoginCommand;
import bookStoragePJ.domain.AuthInfoDTO;
import bookStoragePJ.mapper.MemberMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class MemLoginService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;

	public void execute(LoginCommand loginCommand, BindingResult result, HttpSession session) {
		String memId = loginCommand.getMemId();
		String memPw = loginCommand.getMemPw();

		if (memId != null && !memId.isEmpty()) {
			AuthInfoDTO dto = memberMapper.loginSelect(memId);

			if (dto != null) {
				if (dto.getUserEmailCheck() == null || dto.getUserEmailCheck().equals("N")) {
					// 아이디는 있지만, 이메일 인증이 완료되지 않았을 때
					result.rejectValue("userEmailCheck", "loginCommand.userEmailCheck", "이메일 인증이 완료되지 않았습니다.");
				} else {
					// 아이디도 있고, 이메일 인증도 되었을 때
					if (dto.getGrade().equals("emp")) {
						if (memPw.equals(dto.getMemPw())) {
							session.setAttribute("auth", dto);
						} else {
							result.rejectValue("memPw", "loginCommand.memPw", "비밀번호가 일치하지 않습니다.(관리자)");
						}
					} else {
						if (passwordEncoder.matches(memPw, dto.getMemPw())) {
							// 비밀번호가 DB와 일치할 때
							session.setAttribute("auth", dto);
						} else {
							// 비밀번호가 DB와 일치하지 않을 때
							result.rejectValue("memPw", "loginCommand.memPw", "비밀번호가 일치하지 않습니다.(회원)");
						}
					}

				}
			} else {
				// 아이디가 존재하지 않을 때
				result.rejectValue("memId", "loginCommand.memId", "아이디가 존재하지 않습니다.");
			}
		}
	}
}
