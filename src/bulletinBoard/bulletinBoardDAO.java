package bulletinBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DBconnection.*;
public class bulletinBoardDAO {

	public boolean insertPost(bulletinBoardVO vo) { // 게시글 저장
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "INSERT INTO board (id, Name, text) VALUES (?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getText());

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
	
	public ArrayList<forPostList> getPostList() { // name,id,postNum,time 을담은 객체 List를 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<forPostList> postList = new ArrayList<forPostList>();
		
		try {
			conn = DBconnection.getConnection();
			
			String sql = "select id, name, postNum, time from board";
		      pstmt= conn.prepareStatement(sql);
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					forPostList tmp = new forPostList();
					tmp.setId(rs.getString("id"));
					tmp.setName(rs.getString("Name"));
					tmp.setNum(Integer.parseInt(rs.getString("postNum")));
					tmp.setTime(rs.getString("time"));
					postList.add(tmp);
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

		return postList;
	}
	public ArrayList<forPostList> getPostList(String id, int n) { 
		// id를 통해 검색했을때 사용 , / int n 은 그냥 오버라이드를 위함  / 객체 List를 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<forPostList> postList = new ArrayList<forPostList>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select name, postNum from board where id=?";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setString(1, id);
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					forPostList tmp = new forPostList();
					tmp.setId(rs.getString("id"));
					tmp.setName(rs.getString("Name"));
					tmp.setNum(Integer.parseInt(rs.getString("postNum")));
					tmp.setTime(rs.getString("time"));
					postList.add(tmp);
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

		return postList;
	}
	public ArrayList<forPostList> getPostList(String title) { // 제목을 통해 검색 했을때 사용/ 객체 List를 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<forPostList> postList = new ArrayList<forPostList>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select name, postNum from board where name=?";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setString(1, title);
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					forPostList tmp = new forPostList();
					tmp.setId(rs.getString("id"));
					tmp.setName(rs.getString("Name"));
					tmp.setNum(Integer.parseInt(rs.getString("postNum")));
					tmp.setTime(rs.getString("time"));
					postList.add(tmp);
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

		return postList;
	}
	public bulletinBoardVO getPost(int num) { // 글 번호를 받아 글의 모든정보를 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		bulletinBoardVO vo = new bulletinBoardVO();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select id, name, text, star, postNum from board where postNum=?";
		      
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
		    rs = pstmt.executeQuery();
		      
				while(rs.next()){
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setText(rs.getString("text"));
					vo.setNum(rs.getInt("postNum"));
					vo.setStar(rs.getInt("star"));
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
	
	public boolean deletePost(int postNum) { // 글의 번호를 받아 게시글을 삭제
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "delete from board where postNum=? ";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, postNum);

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
	
	public boolean updatePost(int postNum, String text) { // 글의 번호와 내용을 받아 글의 내용을 변경
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "UPDATE board SET text=? WHERE postNum=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, text);
				pstmt.setInt(2, postNum);

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
	
	public int checkStar(int postNum, String id) { // 게시글의 추천을 누르는 메소드
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();

				String sql = "select id from star where postNum=? and id=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, postNum);
				pstmt.setString(2, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()==false)//해당 id가 추천이 안눌러 져있다면  -1을 반환
					result = -1;
				
				else//해당 id가 추천을 눌렀다면  1을 반환
					result = 1;
				
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
	
	public int changeStar(int postNum, String id) { // 게시글의 추천을 변경하는 메소드
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();

			int check = checkStar(postNum,id);
			String sql="";
			
				if(check==-1){//해당 id가 추천이 안눌러 져있다면 DB에 id를 기록하고 1을 반환
					sql = "insert into star(id, postNum) values(?,?)";
					result = 1;
				}
				else{//해당 id가 추천을 눌렀다면 id를 DB에서 삭제하고 -1을 반환
					sql = "delete from star where id=? and postNum=?";
					result = -1;
				}
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setInt(2, postNum);
				
				pstmt.executeUpdate();
			
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
	
	public int star(int postNum, String id) {// 게시글의 추천수를 변경하여 DB에 저장하는 메소드
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result= 0;
		try {
			conn = DBconnection.getConnection();

				String sql = "select star from board where postNum=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, postNum);
				
				rs = pstmt.executeQuery();
				rs.next();
				int star = rs.getInt("star"); // 해당 게시글의 추천수를 받아온다.
				int checkStar=changeStar(postNum, id);//checkStar을 이용하여 해당 id가 추천을 했는지 안했는지 확인
				result = checkStar;
				star +=checkStar;
				//check로 부터 return 받은 값을 더해 DB에 다시 넣어줌
				sql = "update board set star=? where Postnum=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, star);
				pstmt.setInt(2, postNum);
				pstmt.executeUpdate();
			
				
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

