package bookStoragePJ.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("authInfo")
public class AuthInfoDTO {
	String memId;
	String memPw;
	String memName;
	String grade;
	String userEmail;
	String userEmailCheck;	
}
