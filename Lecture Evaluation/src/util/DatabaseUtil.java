package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnection() {
		try {
			//mysql에 접속하기 위한 코드 tutorial 테이블에 접근
			String dbURL = "jdbc:mysql://localhost:3306/lectureEvaluation";
			//db 정보
			String dbID = "root";
			String dbPassword ="root";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//오류발생시 null 반환
		return null;
	}
}
