package bookStoragePJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import bookStoragePJ.command.FindPwUpdateCommand;
import bookStoragePJ.mapper.FindMapper;

@Service
public class FindPwUpdateService {
	@Autowired
	FindMapper findMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(FindPwUpdateCommand findPwUpdateCommand, Model model) {
		String memId = findPwUpdateCommand.getMemId();
		String memPw = passwordEncoder.encode(findPwUpdateCommand.getMemPw());
		
		findMapper.findPwUpdate(memId, memPw);
	}
}
