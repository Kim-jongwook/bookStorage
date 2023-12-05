package bookStoragePJ.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindPwCommand {

	@NotBlank(message = "아이디를 입력해주세요")
	String memId;	
	@NotBlank(message = "등록하신 전화번호를 입력해주세요.")
	String memPhone;
	@NotEmpty(message = "등록하신 이메일을 입력해주세요.")
	String memEmail;
}
