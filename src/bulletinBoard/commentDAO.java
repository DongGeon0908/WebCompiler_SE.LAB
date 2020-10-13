package bulletinBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DBconnection.*;
public class commentDAO {
	
	public boolean insertComment(String id, String text, int postNum) { // 댓글 저장
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "INSERT INTO comment (id, text, postNum) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2, text);
				pstmt.setInt(3, postNum);

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
	public boolean insertComment2(String id, String text, int commentNum) { // 대댓글 저장
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "INSERT INTO comment2 (id, text, commentNum) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2, text);
				pstmt.setInt(3, commentNum);

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
	public ArrayList<forCommentList> getCommentList(int postNum) { // 게시글 번호를 받아 해당 게시글에 대한 댓글을 ArrayList로 반환 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ArrayList<forCommentList> commentList = new ArrayList<forCommentList>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select id, text, postNum, commentNum from comment where postNum=?";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setInt(1, postNum);
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					forCommentList tmpCommentList = new forCommentList();
					tmpCommentList.setId(rs.getString("id"));
					tmpCommentList.setText(rs.getString("text"));
					tmpCommentList.setPostNum(rs.getInt("postNum"));
					tmpCommentList.setCommentNum(rs.getInt("commentNum"));
					
					sql ="select id, text, comment2Num from comment2 where commentNum=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, rs.getInt("commentNum"));
					
					rs2=pstmt.executeQuery();
					
					ArrayList <forComment2List> tmpComment2List = new ArrayList<forComment2List>();
					while(rs2.next())
					{//댓글에 대한 대댓글이 있을경우 저장
						 forComment2List tmpComment2 = new forComment2List();
						 tmpComment2.setId(rs2.getString("id"));
						 tmpComment2.setText(rs2.getString("text"));
						 tmpComment2.setCommentNum(rs.getInt("commentNum"));
						 tmpComment2.setComment2Num(rs2.getInt("comment2Num"));
						 
						 tmpComment2List.add(tmpComment2);
					}
					tmpCommentList.setComment2List(tmpComment2List);
					commentList.add(tmpCommentList);
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

		return commentList;
	}
	public boolean deleteComment(int commentNum) { // 댓글 삭제
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "delete from comment where commentNum=? ";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, commentNum);

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
	public boolean deleteComment2(int comment2Num) {//대댓글 삭제
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "delete from comment2 where comment2Num=? ";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, comment2Num);

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
}
