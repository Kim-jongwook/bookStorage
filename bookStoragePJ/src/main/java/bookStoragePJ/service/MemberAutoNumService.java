package bookStoragePJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bookStoragePJ.command.MemberCommand;
import bookStoragePJ.mapper.MemberMapper;

@Service
public class MemberAutoNumService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(Model model) {
		String memNum = memberMapper.autoNum();
		MemberCommand memberCommand = new MemberCommand();
		memberCommand.setMemNum(memNum);
		model.addAttribute("memberCommand", memberCommand);
	}
}
