package bookStoragePJ.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("find")
public class FindDTO {
	String memId;
	String memName;
	String memPhone;
	String memEmail;
	String memPw;
}
