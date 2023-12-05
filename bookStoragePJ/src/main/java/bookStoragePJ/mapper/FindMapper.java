package bookStoragePJ.mapper;

import org.apache.ibatis.annotations.Mapper;

import bookStoragePJ.domain.FindDTO;

@Mapper
public interface FindMapper {
	public String findId(String memName, String memPhone, String memEmail);
	public FindDTO findPw(String memId, String memPhone, String memEmail);
	public void findPwUpdate(String memId, String memPw);
	public void findPwSend(FindDTO dto);
}
