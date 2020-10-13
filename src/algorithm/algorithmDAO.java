package algorithm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DBconnection.*;
import code.codeVO;
public class algorithmDAO {
	
	public boolean updateAlgorithm(algorithmVO vo, int num) {//알고리즘 업데이트
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "update algorithm set category=?, name=?, explanation=?, exinput=?, exoutput=?, input=?, output=? where algorithmNum=?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getCategory());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getExplanation());
				pstmt.setString(4, vo.getExInput());
				pstmt.setString(5, vo.getExOutput());
				pstmt.setString(6, vo.getInput());
				pstmt.setString(7, vo.getOutput());
				pstmt.setInt(8, num);

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
	
	public boolean insertAlgorithm(algorithmVO vo) {//알고리즘 저장
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();

				String sql = "INSERT INTO algorithm (category,name,explanation,exinput,exoutput,input,output) VALUES (?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getCategory());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getExplanation());
				pstmt.setString(4, vo.getExInput());
				pstmt.setString(5, vo.getExOutput());
				pstmt.setString(6, vo.getInput());
				pstmt.setString(7, vo.getOutput());

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
	
	public algorithmVO getAlgorithm(int num) { // algorithm 내용 불러오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		algorithmVO vo = new algorithmVO();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from algorithm where algorithmNum=?;";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setInt(1, num);
		      
		      rs = pstmt.executeQuery();
		      
				if(rs.next()){
					
					vo.setName(rs.getString("name"));
					vo.setExplanation(rs.getString("explanation"));
					vo.setExInput(rs.getString("Exinput"));
					vo.setExOutput(rs.getString("Exoutput"));
					vo.setInput(rs.getString("input"));
					vo.setOutput(rs.getString("output"));
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
	
	public ArrayList<algorithmVO> getAlgorithmList() { // algorithm 전체 불러오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<algorithmVO> algoritymList = new ArrayList<algorithmVO>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from algorithm ;";
		      pstmt= conn.prepareStatement(sql);
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					algorithmVO tmp = new algorithmVO();
					tmp.setName(rs.getNString("name"));
					tmp.setNum(rs.getInt("algorithmNum"));
					
					algoritymList.add(tmp);
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

		return algoritymList;
	}
	
	public ArrayList<algorithmVO> getAlgorithmList(String category) { // algorithm 카테고리별로 불러오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<algorithmVO> algoritymList = new ArrayList<algorithmVO>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from algorithm where category=?;";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setString(1, category);
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					algorithmVO tmp = new algorithmVO();
					tmp.setName(rs.getString("name"));
					tmp.setNum(rs.getInt("algorithmNum"));
					
					algoritymList.add(tmp);
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

		return algoritymList;
	}
	
	public int doesUserTry(String id, int num) { // user가 해당 알고리즘을 시도했는지 결과 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from user_algorithm_data where id=? and algorithmNum=?;";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setString(1, id);
		      pstmt.setInt(2, num);
		      
		      rs = pstmt.executeQuery();
		      
				if(rs.next()){
					result = rs.getInt("result");
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
	
	public ArrayList<userAlgorithmCodeVO> getAlgorithmCodeList(String id, int num) { // user가 작성한 algorithmCode 불러오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<userAlgorithmCodeVO> algorithmCodeList = new ArrayList<userAlgorithmCodeVO>();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from user_algorithm_code where id=? and algorithmNum=?;";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setString(1, id);
		      pstmt.setInt(2, num);
		      
		      rs = pstmt.executeQuery();
		      
				while(rs.next()){
					userAlgorithmCodeVO tmp = new userAlgorithmCodeVO();
					tmp.setCodeNum(rs.getInt("codeNum"));
					tmp.setCode(rs.getString("code"));
					tmp.setCodeType(rs.getString("codeType"));
					tmp.setResult(rs.getInt("result"));
					algorithmCodeList.add(tmp);
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

		return algorithmCodeList;
	}
	
	public userAlgorithmCodeVO getAlgorithmCode(String id, int codeNum) { // algorithm 내용 불러오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		userAlgorithmCodeVO vo = new userAlgorithmCodeVO();
		
		try {
			conn = DBconnection.getConnection();

			String sql = "select * from user_algorithm_code where id=? and codeNum=?;";
		      pstmt= conn.prepareStatement(sql);
		      
		      pstmt.setString(1, id);
		      pstmt.setInt(2, codeNum);
		      
		      rs = pstmt.executeQuery();
		      
				if(rs.next()){
					vo.setCodeType(rs.getString("codeType"));
					vo.setCode(rs.getString("code"));
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
