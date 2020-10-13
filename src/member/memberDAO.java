package member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBconnection.DBconnection;

public class memberDAO {
	

	public void dbinsert(memberVO vo) {         // ȸ�����Խ� ������  db�� �Է��ϱ� ���� �޼ҵ�
	      Connection conn=null;
	       PreparedStatement pstmt = null;
	       int rs = 0;
	       
	       try {
	    	   conn = DBconnection.getConnection();
	         
	         String sql = "insert into user values (?, ?, ?, ?, ?, ?);";
	         String sql2 = "insert into workspaceUserData values (?);";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1,vo.id);
	         pstmt.setString(2,vo.pw);
	         pstmt.setString(3,vo.name);
	         pstmt.setString(4,vo.mail);
	         pstmt.setString(5,vo.info);
	         pstmt.setString(6,"user");
	         
	         rs = pstmt.executeUpdate();//DB - user table �� ���� ����
	         
	         pstmt.close();
	         
	         pstmt = conn.prepareStatement(sql2);
	         pstmt.setString(1,vo.id);
	         rs = pstmt.executeUpdate();//DB - workSpaceUserData �� user Id ����
	         
	         
	         pstmt.close();
	         conn.close();
	         
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	   }
	
	public int idCheck(String id){   //id�� db���� Ȯ���Ͽ� �ߺ� üũ�ϱ� ���� �޼ҵ�
        int rst = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        	conn = DBconnection.getConnection();
         String sql = "select * from user where user_id=?";
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);
         rs = ps.executeQuery();
         if(rs.next()){
          rst = 1;
         }
        }catch(Exception e){
         e.printStackTrace();
        }
        return rst;
       }
	
	public String dologin(memberVO vo) {// id�� pw�� üũ�ؼ� �α��� ����� �ϴ� �Լ� ��ȯ���� ��Ʈ���̸� ������ ������ ��ȯ�Ѵ�.
			Connection conn=null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
			String result = "fail"; 
			
			try {
				conn = DBconnection.getConnection();
				
				String sql = "select user_pw , user_authority from user where user_id=?";
			    
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
		        rs = pstmt.executeQuery();
				
				if (rs.next()) {
		        	result="pwMiss";//id �� �ִ� (id �� pw �� ��ġ���� ������� ������� �ʰ� ��ȯ��)
		            String dbpw= rs.getString("user_pw");//db�� pw ����
		            if(dbpw.equals(vo.getPw()))
		            	result=rs.getString("user_authority");// �α��� ������ ������ ������ ��ȯ
		        } 
		        else 
		        	result = "idMiss"; //id�� �������� ����

				rs.close();
				pstmt.close();
				
			}
			catch(SQLException e) {
				System.out.println("����: " + e);
			}
			
		return result;
	}

	public memberVO getMemberVO(String id) { // ������� ������ ��ȯ��
			memberVO user=new memberVO();
			Connection conn=null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	       
	       try {
	    	   conn = DBconnection.getConnection();
	         
	         String sql = "select user_name, user_Email, user_introduce from user where user_id=?";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1,id);
	         
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	         user.setName(rs.getString("user_name"));
	         user.setMail(rs.getString("user_Email"));
	         user.setInfo(rs.getString("user_introduce"));
	         
	         pstmt.close();
	         conn.close();
	         
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	       return user;
	   }
	
	public boolean modifyUserInfo(memberVO vo) { // user�� ������ �����ϴ� �޼ҵ�
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBconnection.getConnection();
			
			if(vo.getPw().isEmpty()==true) { // ��й�ȣ ���� ���� �ٸ� ������ �����Ͽ��� ���
				String sql="UPDATE user SET user_name=?, user_email=?, user_introduce=? WHERE user_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getMail());
				pstmt.setString(3, vo.getInfo());
				pstmt.setString(4, vo.getId());
			}
			else{// ��й�ȣ�� �����Ͽ��� ���
				String sql="UPDATE user SET user_name=?, user_pw=?, user_email=?, user_introduce=? WHERE user_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPw());
				pstmt.setString(3, vo.getMail());
				pstmt.setString(4, vo.getInfo());
				pstmt.setString(5, vo.getId());
			}
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
	
	public String findId(memberVO vo) { // name�� eamil�� �޾� id�� ��ȯ, ��ġ�ϴ� id�� ���ٸ� fail�̶�� ���ڿ� ��ȯ
		String result="fail";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBconnection.getConnection();

			String sql="select user_id from user where user_name=? and user_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMail());
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				result= rs.getString("user_id");
			
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
	
	public boolean changePw(memberVO vo) { // pw�� �ٲٴ� �޼ҵ�
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBconnection.getConnection();

			String sql="UPDATE user SET user_pw=? WHERE user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getId());
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
	public boolean checkIdnEmail(memberVO vo) { // email ������ üũ�ϴ� �޼ҵ�
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;

		try {
			conn = DBconnection.getConnection();

			String sql="select user_id from user where user_id=? and user_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getMail());

			rs = pstmt.executeQuery();

			if(rs.next())
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
	
}