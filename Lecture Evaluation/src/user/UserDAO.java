package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {

	//�α��� �Լ�
	//���̵�� ��й�ȣ�� �޾Ƽ� �������ִ� �Լ�
	//����� ���������� ��ȯ
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		//�� �� �ʱ�ȭ �۾�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//������ ����� �� �Լ��� �̿��ؼ� �����ͺ��̽� ����
			//����� ��ü�� conn ��ü�� ���� ��
			conn = DatabaseUtil.getConnection();
			// pstmt��ü�� �̿��ؼ� conn��ü���� ���� �����ص� sql ������ ������ �� �ֵ��� �غ��ϴ� ����
			pstmt = conn.prepareStatement(SQL);
			// ����ڷκ��� �Է��� ���� id���� ?�ȿ��ٰ� �־���
			pstmt.setString(1,  userID);
			//������ �� sql������ ������� ����� rs��ü�� �����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //�α��� ����
				} 
				else {
					return 0; //��й�ȣ Ʋ��
				}
			}
			return -1; // ���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(rs != null) rs.close(); } catch(Exception e) {	e.printStackTrace(); }
		}
		return -2; // �����ͺ��̽� ����
	}
	
	// ȸ������ �Լ�
	//������� ������ �Է¹޾Ƽ� ȸ�������� �������ִ� �Լ�
	//����� ������ ��ȯ
	public int join(UserDTO user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, false)";
		//�� �� �ʱ�ȭ �۾�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//������ ����� �� �Լ��� �̿��ؼ� �����ͺ��̽� ����
			//����� ��ü�� conn ��ü�� ���� ��
			conn = DatabaseUtil.getConnection();
			// pstmt��ü�� �̿��ؼ� conn��ü���� ���� �����ص� sql ������ ������ �� �ֵ��� �غ��ϴ� ����
			pstmt = conn.prepareStatement(SQL);
			// ����ڷκ��� �Է��� ���� id���� ?�ȿ��ٰ� �־���
			pstmt.setString(1,  user.getUserID());
			pstmt.setString(2,  user.getUserPassword());
			pstmt.setString(3,  user.getUserEmail());
			pstmt.setString(4,  user.getUserEmailHash());
			//executeQuery�� �����͸� �˻�(��ȸ)�� �� ��� 
			//executeUpdate�� insert delete update���� �Ҷ� ��� | ������ ������ ���� �������� ������ ��ȯ
			//������ �ϳ��� �߰��� 1�̶�� �� ��ȯ, ���н� -1
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { if(conn != null) conn.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(pstmt != null) pstmt.close(); } catch(Exception e) {	e.printStackTrace(); }
			try { if(rs != null) rs.close(); } catch(Exception e) {	e.printStackTrace(); }
		}
		return -1; // ȸ������ ����
	}
	
	//������� ���̵��� �޾Ƽ� ������� eamil �ּҸ� ��ȯ���ִ� �Լ�
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";
		//�� �� �ʱ�ȭ �۾�
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
		return null; // �����ͺ��̽� ����
	}
	
	//����ڰ� email������ �Ǿ����� Ȯ�����ִ� �Լ�
	public boolean getUserEmailChecked(String userID) {
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";
		//�� �� �ʱ�ȭ �۾�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			// ȸ�� ���̵� ��ȸ�Ŀ� ���̵� ������ ��� 1(true)�� ��ȯ�ؼ� ����, �ƴ� ��� false�� ��ȯ�ؼ� ���и� �˸�
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
		return false; // �����ͺ��̽� ����
	}
	
	//eamil ������ �������ִ� �Լ�
	public boolean setUserEmailChecked(String userID) {
		String SQL = "UPDATE USER SET userEmailChecked = true WHERE userID = ?";
		//�� �� �ʱ�ȭ �۾�
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
		return false; // �����ͺ��̽� ����
	}
}
