package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBconnection.*;

public class codeDAO {

	public boolean insertCode(codeVO vo) {// ���ø� �ڵ� �и� �� �ڵ� ����
		String code="";
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String splitCode = "D3EA7KG44QW1ER0458,|D3EA7KG44QW1ER0458";// �� �ڵ带 �������� split ����
		String[] afterSplitCode = vo.getCode().split(splitCode);//split�ؼ� ���κ��� �迭�� ����
		for (int i = 0; i < afterSplitCode.length; i++) {//����+���๮�ڸ� ���� �ϳ��� ������ ����
			code += afterSplitCode[i];
			code += "\\n";
		}
		try {
			conn = DBconnection.getConnection();

				String sql = "INSERT INTO workspace (user_id, codeName, codeType, code) VALUES (?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getUser_id());
				pstmt.setString(2, vo.getCodeName());
				pstmt.setString(3, vo.getCodeType());
				pstmt.setString(4, code);

				pstmt.executeUpdate();

				result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public boolean deleteCode(String user_id, String codeName) {//�ڵ� ����
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "delete from workspace where user_id=? and codeName=? ";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, user_id);
				pstmt.setString(2, codeName);

				pstmt.executeUpdate();
				result = true;

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String getCode(String id, String codeName) {//�ڵ带 �ҷ���
		String code="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();

				String sql = "SELECT code FROM workspace WHERE user_id=? AND codeName=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2, codeName);
				rs = pstmt.executeQuery();
				if(rs.next())
					code = rs.getString("code");
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return code;
	}
	
	public String getCodeType(String id, String codeName) {// �ش� �ڵ��� �� ��ȯ
		String codeType = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();

				String sql = "SELECT codetype FROM workspace WHERE user_id=? AND codeName=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2, codeName);
				rs = pstmt.executeQuery();
				if(rs.next())
					codeType = rs.getString("codetype");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return codeType;
	}
	
	public ArrayList<String> getCodeList(String user_id, String codeType) { // codeType�� �´� �ڵ���� �ҷ���
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> codeList = new ArrayList<String>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select codeName from workspace where user_id=? AND codeType=?;";
		      pstmt= conn.prepareStatement(sql);
		      pstmt.setString(1, user_id);
		      pstmt.setString(2, codeType);
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					codeList.add(rs.getString("codeName"));
					}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return codeList;
	}
	
	public boolean updateCode(codeVO vo) { // �ڵ峻�� ����
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

			String sql="UPDATE workspace SET code=?, codeType=? WHERE user_id=? AND codeName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCode());
			pstmt.setString(2, vo.getCodeType());
			pstmt.setString(3, vo.getUser_id());
			pstmt.setString(4, vo.getCodeName());
			pstmt.executeUpdate();
				
			result= true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public String codeNameCheck(String id, String codeName) { // db�� �ڵ��̸��� �Է¹��� �ڵ��̸��� ������ Ȯ���ϴ� �Լ� ������ flase �ߺ��� ������ true
																// ����
		String result = "true";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBconnection.getConnection();

			String sql = "select codeName from workspace where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {// db�� �ִ� �ڵ��̸��� ���� �Է¹��� �ڵ� �̸��� ������ Ȯ��
				String dbCodeName = rs.getString("codeName");
				if (dbCodeName.equals(codeName)) {
					result = "false";// �ߺ���
					break;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}