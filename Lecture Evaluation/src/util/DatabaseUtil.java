package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection getConnection() {
		try {
			//mysql�� �����ϱ� ���� �ڵ� tutorial ���̺� ����
			String dbURL = "jdbc:mysql://localhost:3306/lectureEvaluation";
			//db ����
			String dbID = "root";
			String dbPassword ="root";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�����߻��� null ��ȯ
		return null;
	}
}
