package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
   public static Connection getConnection() throws SQLException {
       Connection conn = null;
      
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         //드라이버 로딩
         String url = "jdbc:mysql://localhost:3306/seEditor?serverTimezone=UTC";
         //DB url 설정 및 시간설정
         //?serverTimezone=UTC시간설정부분
         conn = DriverManager.getConnection(url, "SELAB", "1234");

         //DB url +id + pw
      }
      catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("오류: " + e);
        }
      return conn;
   }
}