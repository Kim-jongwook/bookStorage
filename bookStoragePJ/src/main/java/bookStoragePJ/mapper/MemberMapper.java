package bookStoragePJ.mapper;

import org.apache.ibatis.annotations.Mapper;

import bookStoragePJ.domain.AuthInfoDTO;
import bookStoragePJ.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public String autoNum();
	public int memInsert(MemberDTO dto);
	public int memCheckUpdate(String email);
	public AuthInfoDTO loginSelect(String memId);
	public String selectIdCheck(String userId);
	public String selectEmailCheck(String userEmail);
}
