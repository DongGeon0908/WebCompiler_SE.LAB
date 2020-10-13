package inquiry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBconnection.DBconnection;

public class inquiryDAO {

	public boolean insertInquiry(String id, String title, String text) { // 문의하기 저장
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "INSERT INTO inquiry (id, title, text) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2, title);
				pstmt.setString(3, text);

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
	
	public inquiryVO getInquiry(int num) {// 번호를 입력받아 문의하기 글에 대한 정보를 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		inquiryVO vo = new inquiryVO();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select id, title, text, Num from inquiry where inquiryNum=?";
		      
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
		    rs = pstmt.executeQuery();
		      
				while(rs.next()){
					vo.setId(rs.getString("id"));
					vo.setTitle(rs.getString("title"));
					vo.setText(rs.getString("text"));
					vo.setNum(rs.getInt("inquiryNum"));
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

		return vo;
	}
	
	public ArrayList<inquiryVO> getInquiryList() { // 문의하기의 글 중 답글이 안달린 것을 모두 반환 -> 관리자가 사용할 것
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<inquiryVO> inquiryList = new ArrayList<inquiryVO>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from inquiry";
		      pstmt= conn.prepareStatement(sql);
		      
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					if(rs.getInt("comment")==1)
						continue;
					
					inquiryVO tmp = new inquiryVO();
					tmp.setId(rs.getString("id"));
					tmp.setTitle(rs.getString("title"));
					tmp.setText(rs.getString("text"));
					tmp.setNum(rs.getInt("inquiryNum"));
					tmp.setComment(rs.getInt("comment"));
					
					inquiryList.add(tmp);
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

		return inquiryList;
	}
	
	public ArrayList<inquiryVO> getInquiryList(String id) { // 해당 id가 작성한 문의내용을 List로 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<inquiryVO> inquiryList = new ArrayList<inquiryVO>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from inquiry where id=?";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setString(1, id);
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					inquiryVO tmp = new inquiryVO();
					tmp.setId(rs.getString("id"));
					tmp.setTitle(rs.getString("title"));
					tmp.setText(rs.getString("text"));
					tmp.setNum(rs.getInt("inquiryNum"));
					tmp.setComment(rs.getInt("comment"));
					
					inquiryList.add(tmp);
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

		return inquiryList;
	}
	
	public boolean insertInquiryComment(String id, String title, String text, int inquiryNum) { //문의에 대한 답변 작성
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "INSERT INTO inquiryComment (id, title, text, inquiryNum) VALUES (?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2, title);
				pstmt.setString(3, text);
				pstmt.setInt(4, inquiryNum);

				pstmt.executeUpdate();//답변을 DB에 저장
				
				sql = "update inquiry set comment=? where inquiryNum=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, 1);
				pstmt.setInt(2, inquiryNum);
				
				pstmt.executeUpdate();// 해당 문의글의 상태를 답변완료로 변경
				
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
	public inquiryCommentVO getInquiryComment(int inquiryNum) { // 문의에 달린 답변을 불러오는 메소드
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		inquiryCommentVO vo = new inquiryCommentVO();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select id, title, text, inquiryNum from inquiryComment where inquiryNum=?";
		      
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, inquiryNum);
			
		    rs = pstmt.executeQuery();
		      
				while(rs.next()){
					vo.setId(rs.getString("id"));
					vo.setTitle(rs.getString("title"));
					vo.setText(rs.getString("text"));
					vo.setInquiryNum(rs.getInt("inquiryNum"));
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

		return vo;
	}
}
