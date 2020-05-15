package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {

	//로그인 함수
	//아이디와 비밀번호를 받아서 실행해주는 함수
	//결과는 정수형으로 반환
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		//각 값 초기화 작업
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//기존에 만들어 둔 함수를 이용해서 데이터베이스 접속
			//연결된 객체는 conn 객체에 담기게 됨
			conn = DatabaseUtil.getConnection();
			// pstmt객체를 이용해서 conn객체에서 위에 정의해둔 sql 문장을 실행할 수 있도록 준비하는 과정
			pstmt = conn.prepareStatement(SQL);
			// 사용자로부터 입력을 받은 id값을 ?안에다가 넣어줌
			pstmt.setString(1,  userID);
			//실제로 위 sql문장을 실행시켜 결과를 rs객체에 담아줌
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				} 
				else {
					return 0; //비밀번호 틀림
				}
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(rs != null) rs.close(); } catch(Exception e) {	e.printStackTrace(); }
		}
		return -2; // 데이터베이스 오류
	}
	
	// 회원가입 함수
	//사용자의 정보를 입력받아서 회원가입을 수행해주는 함수
	//결과는 정수로 반환
	public int join(UserDTO user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, false)";
		//각 값 초기화 작업
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//기존에 만들어 둔 함수를 이용해서 데이터베이스 접속
			//연결된 객체는 conn 객체에 담기게 됨
			conn = DatabaseUtil.getConnection();
			// pstmt객체를 이용해서 conn객체에서 위에 정의해둔 sql 문장을 실행할 수 있도록 준비하는 과정
			pstmt = conn.prepareStatement(SQL);
			// 사용자로부터 입력을 받은 id값을 ?안에다가 넣어줌
			pstmt.setString(1,  user.getUserID());
			pstmt.setString(2,  user.getUserPassword());
			pstmt.setString(3,  user.getUserEmail());
			pstmt.setString(4,  user.getUserEmailHash());
			//executeQuery는 데이터를 검색(조회)할 때 사용 
			//executeUpdate는 insert delete update등을 할때 사용 | 실제로 영향을 받은 데이터의 갯수를 반환
			//성공시 하나가 추가되 1이라는 값 반환, 실패시 -1
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(rs != null) rs.close(); } catch(Exception e) {	e.printStackTrace(); }
		}
		return -1; // 회원가입 실패
	}
	
	//사용자의 아이디값을 받아서 사용자의 eamil 주소를 반환해주는 함수
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";
		//각 값 초기화 작업
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(rs != null) rs.close(); } catch(Exception e) {	e.printStackTrace(); }
		}
		return null; // 데이터베이스 오류
	}
	
	//사용자가 email인증이 되었는지 확인해주는 함수
	public boolean getUserEmailChecked(String userID) {
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";
		//각 값 초기화 작업
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			// 회원 아이디 조회후에 아이디가 존재할 경우 1(true)를 반환해서 성공, 아닐 경우 false를 반환해서 실패를 알림
			if(rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(rs != null) rs.close(); } catch(Exception e) {	e.printStackTrace(); }
		}
		return false; // 데이터베이스 오류
	}
	
	//eamil 인증을 실행해주는 함수
	public boolean setUserEmailChecked(String userID) {
		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";
		//각 값 초기화 작업
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(rs != null) rs.close(); } catch(Exception e) {	e.printStackTrace(); }
		}
		return false; // 데이터베이스 오류
	}
}
