package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		//�̸����� ������ ���� ���̵� ��й�ȣ �Է�
		return new PasswordAuthentication("���̵�", "��й�ȣ");
	}
}