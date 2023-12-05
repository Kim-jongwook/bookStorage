package bookStoragePJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import bookStoragePJ.command.FindPwCommand;
import bookStoragePJ.domain.FindDTO;
import bookStoragePJ.mapper.FindMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class FindPwService {
	@Autowired
	FindMapper findMapper;
	
	public void execute(FindPwCommand findPwCommand, BindingResult result, Model model, HttpSession session) {
		String memId = findPwCommand.getMemId();
		String memPhone = findPwCommand.getMemPhone();
		String memEmail = findPwCommand.getMemEmail();
		
		FindDTO findDTO = findMapper.findPw(memId, memPhone, memEmail);
		if(findDTO.getMemPw() == null || findDTO.getMemPw().equals("")) {
			result.rejectValue("memPw", "findIdCommand.memPw", "기재하신 정보가 일치하지 않습니다.");
		}else {
			session.setAttribute("findDTO", findDTO);
		}
	}
}
