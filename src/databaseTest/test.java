package databaseTest;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/dbTest")
public class test extends HttpServlet
{

 public void service(ServletRequest req, ServletResponse res)
     throws ServletException, IOException
 {
     System.out.println("service called");
     super.service(req, res);
 }

 protected void doPost(HttpServletRequest req, HttpServletResponse resp)
     throws ServletException, IOException
 {
     PrintWriter out;
     String id;
     String pw;
     String name;
     String email;
     String intro;
     String auth;
     String url;
     String sql_id;
     String sql_pwd;
     resp.setContentType("text/html;charset=UTF-8");
     req.setCharacterEncoding("UTF-8");
     out = resp.getWriter();
     id = req.getParameter("user_id");
     pw = req.getParameter("user_pw");
     name = req.getParameter("user_name");
     email = req.getParameter("user_email");
     intro = req.getParameter("user_intro");
     auth = req.getParameter("user_auth");
     Statement stmt = null;
     ResultSet rs = null;
     //Connection con = null;
     url = "jdbc:mysql://localhost:3306/seEditor";
     sql_id = "SELAB";
     sql_pwd = "1234";
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection(url, sql_id, sql_pwd);
         PreparedStatement pstmt = con.prepareStatement("INSERT INTO user(user_id, user_pw, user_name, user_email, user_introduce, user_authority) VALUES(?,?,?,?,?,?)");
         pstmt.setString(1, id);
         pstmt.setString(2, pw);
         pstmt.setString(3, name);
         pstmt.setString(4, email);
         pstmt.setString(5, intro);
         pstmt.setString(6, auth);
         pstmt.executeUpdate();
         System.out.println("DB 저장 성공 !!");
         out.print("<html><head><title>DB 저장에 성공 </title></head>");
         out.print("<body>");
         out.print("<h1>DB에 저장을 성공하였습니다. !</h1>");
         out.print("</body></html>");
         out.close();
         pstmt.close();
         con.close();
     }
     catch(Exception e)
     {
         e.printStackTrace();
         out.print("<html><head><title>DB \uC800\uC7A5 \uC2E4\uD328</title></head>");
         out.print("<body>");
         out.print("<h1>DB\uC5D0 \uC800\uC7A5\uC744 \uC2E4\uD328\uD588\uC2B5\uB2C8\uB2E4...</h1>");
         out.print("</body></html>");
         out.close();
     }
     return;
 }
}