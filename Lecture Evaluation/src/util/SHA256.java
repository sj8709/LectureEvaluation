package util;

import java.security.MessageDigest;

public class SHA256 {

	
	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			//실제로 hash를 적용한 값을 chars에 담아줌
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			//문자열 값으로 만듬
			for(int i=0; i<chars.length; i++) {
				//hex값과 hash값을 적용한 값을 and연산
				String hex = Integer.toHexString(0xff & chars[i]);
				// 한자리일 경우 0을 붙여서 총 두자리 값을 가지는 16진수  형태로 만듬
				if(hex.length() == 1) result.append("0");
				result.append(hex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
