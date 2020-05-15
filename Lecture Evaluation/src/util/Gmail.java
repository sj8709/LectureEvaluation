package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		//이메일을 전송할 구글 아이디 비밀번호 입력
		return new PasswordAuthentication("아이디", "비밀번호");
	}
}
