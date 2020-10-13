package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
   public static Connection getConnection() throws SQLException {
       Connection conn = null;
      
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         //����̹� �ε�
         String url = "jdbc:mysql://localhost:3306/seEditor?serverTimezone=UTC";
         //DB url ���� �� �ð�����
         //?serverTimezone=UTC�ð������κ�
         conn = DriverManager.getConnection(url, "SELAB", "1234");

         //DB url +id + pw
      }
      catch(ClassNotFoundException e){
            System.out.println("����̹� �ε� ����");
        }
        catch(SQLException e){
            System.out.println("����: " + e);
        }
      return conn;
   }
}