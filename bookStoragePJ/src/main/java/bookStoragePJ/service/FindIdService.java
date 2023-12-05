package bookStoragePJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bookStoragePJ.command.FindIdCommand;
import bookStoragePJ.mapper.FindMapper;

@Service
public class FindIdService{
	@Autowired
	FindMapper findMapper;
	
	public void execute(FindIdCommand findIdCommand, Model model) {
		String memName = findIdCommand.getMemName();
		String memPhone = findIdCommand.getMemPhone();
		String memEmail = findIdCommand.getMemEmail();
		String memId = findMapper.findId(memName, memPhone, memEmail);
		model.addAttribute("memId", memId);
	}
}