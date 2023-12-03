package bookStoragePJ.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("mem")
public class MemberDTO {
	String memNum;
	String memId;
	String memPw;
	String memPwCon;
	String memName;
	String memAddr;
	String memAddrDetail;
	String memPost;
	String memGender;
	String memPhone;
	String memEmail;
	Date memBirth;
	Date memRegist;
	Integer memPoint;
}
