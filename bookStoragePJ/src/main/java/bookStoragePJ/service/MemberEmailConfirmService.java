package bookStoragePJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookStoragePJ.mapper.MemberMapper;

@Service
public class MemberEmailConfirmService {
	@Autowired
	MemberMapper memberMapper;
	public int execute(String email) {
		int i = memberMapper.memCheckUpdate(email);
		return i;
	}
}
