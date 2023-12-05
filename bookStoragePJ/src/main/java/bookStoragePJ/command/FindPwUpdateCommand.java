package bookStoragePJ.command;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FindPwUpdateCommand {
	
	String memId;
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[!@#$%^&*(),.]).{10,16}$", message = "영문, 숫자, 특수문자가 포함된 10~16자리 이어야 합니다.")
	String memPw;
	String memPwCon;
}
