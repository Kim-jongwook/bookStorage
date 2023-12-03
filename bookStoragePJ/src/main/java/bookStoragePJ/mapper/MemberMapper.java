package bookStoragePJ.mapper;

import org.apache.ibatis.annotations.Mapper;

import bookStoragePJ.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public String autoNum();
	public int memInsert(MemberDTO dto);
}
