package linux;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 리눅스 한글 패치가 필요한 상황!
@WebServlet("/shellCompile_java")
public class shellCompile_java extends HttpServlet {

   protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html;charset=UTF-8");
      req.setCharacterEncoding("UTF-8");

      PrintWriter out = resp.getWriter();
      String input = req.getParameter("code");

      String realCode = "";
      String splitCode = "D3EA7KG44QW1ER0458,|D3EA7KG44QW1ER0458";// 이 코드를 기점으로 split 실행
      String[] afterSplitCode = input.split(splitCode);

      for (int i = 0; i < afterSplitCode.length; i++) {
         realCode = realCode + "\n" + afterSplitCode[i];
      }

      ///

      try {
         String check = midSearch(realCode);
         if (check.equals("불법접근입니다.")) {
            out.print(check);

            FileWriter pw = new FileWriter("/usr/local/apache/docker/basic/log.txt", true);
            HttpSession session = req.getSession();
            String user_id = (String) session.getAttribute("id"); // 회원 아이디
            Date initTime = new Date(session.getCreationTime()); // 최초 세션 생성 시각
            Date recentTime = new Date(session.getLastAccessedTime()); // 최근 세션 접근 시각
            pw.write(user_id + " / " + getClientIp(req) + " / " + initTime + " / " + recentTime + " / " + "불법접근"
                  + " / c / " + "불법 언어 접근" + "\n");
            pw.close();
         } else {

            FileWriter javaTest = new FileWriter("/usr/local/apache/share/javaTest.txt");
            javaTest.write(realCode);
            javaTest.close();
///////////////////

            String docker = Reader("/usr/local/apache/docker/basic/docker.txt");
            FileWriter dockerCycle = new FileWriter("/usr/local/apache/docker/basic/docker.txt");
            // out.print("<h3> Docker : " + docker + "</h3>");

            ArrayList<String> DN = new ArrayList<String>();

            shellCmd("sh /usr/local/apache/docker/check.sh");
            File check01 = new File("/usr/local/apache/docker/basic/check01.txt");
            File check02 = new File("/usr/local/apache/docker/basic/check02.txt");
            File check03 = new File("/usr/local/apache/docker/basic/check03.txt");

            if (check01.length() == 0) {
               DN.add("se01");
            }
            if (check02.length() == 0) {
               DN.add("se02");
            }
            if (check03.length() == 0) {
               DN.add("se03");
            }
            int length = DN.size();

            if (length == 1) {
               shellCmd("sh /usr/local/apache/docker/basic/" + DN.get(0) + "/javaMid_" + DN.get(0) + ".sh");
               dockerCycle.write(DN.get(0));
               dockerCycle.close();
            } else if (length == 2) {
               if (docker.indexOf(DN.get(0)) != -1) {
                  shellCmd("sh /usr/local/apache/docker/basic/" + DN.get(0) + "/javaMid_" + DN.get(0) + ".sh");
                  dockerCycle.write(DN.get(1));
                  dockerCycle.close();
               } else if (docker.indexOf(DN.get(1)) != -1) {
                  shellCmd("sh /usr/local/apache/docker/basic/" + DN.get(1) + "/javaMid_" + DN.get(1) + ".sh");
                  dockerCycle.write(DN.get(0));
                  dockerCycle.close();
               }
            } else if (length == 3) {
               if (docker.indexOf("se01") != -1) {
                  shellCmd("sh /usr/local/apache/docker/basic/se01/javaMid_se01.sh");
                  dockerCycle.write("se02");
                  dockerCycle.close();
               } else if (docker.indexOf("se02") != -1) {
                  shellCmd("sh /usr/local/apache/docker/basic/se02/javaMid_se02.sh");
                  dockerCycle.write("se03");
                  dockerCycle.close();
               } else if (docker.indexOf("se03") != -1) {
                  shellCmd("sh /usr/local/apache/docker/basic/se03/javaMid_se03.sh");
                  dockerCycle.write("se01");
                  dockerCycle.close();
               } else {
                  docker = "se01";
                  shellCmd("sh /usr/local/apache/docker/basic/se01/javaMid_se01.sh");
                  dockerCycle.write("se02");
                  dockerCycle.close();
               }
            }

///////////////
            File javaResultNew = new File("/usr/local/apache/share/javaResult.txt");

            String logResult = null;
            if (javaResultNew.length() != 0) {
               String result = Reader("/usr/local/apache/share/javaResult.txt");
               out.print("<h2>Result</h2>");
               out.print("<h5>" + result + "</h5>");
               logResult = "success";
            } else {
               String error = Reader("/usr/local/apache/share/javaError.txt");
               out.print("<h2>Error</h2>");
               out.print("<h5>" + error + "</h5>");
               logResult = "fail";
            }

            docker = docker.replace("<br>", "");
            FileWriter pw = new FileWriter("/usr/local/apache/docker/basic/log.txt", true);
            HttpSession session = req.getSession();
            String user_id = (String) session.getAttribute("id"); // 회원 아이디
            Date initTime = new Date(session.getCreationTime()); // 최초 세션 생성 시각
            Date recentTime = new Date(session.getLastAccessedTime()); // 최근 세션 접근 시각
            pw.write(user_id + " / " + getClientIp(req) + " / " + initTime + " / " + recentTime + " / " + docker
                  + " / java / " + logResult + "\n");
            pw.close();
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doHandle(req, resp);
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doHandle(req, resp);
   }

   public static String shellCmd(String command) throws Exception {
      Runtime runTime = Runtime.getRuntime();
      Process process = runTime.exec(command);
      InputStream inputStream = process.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferReader = new BufferedReader(inputStreamReader);
      String line;
      String result = "";
      while ((line = bufferReader.readLine()) != null) {
         result = result + "<br>" + line;
      }
      return result;
   }

   public static String Reader(String fileName) throws IOException {
      BufferedReader bufferReader = new BufferedReader(new FileReader(fileName));
      String result = "";
      while (true) {
         String line = bufferReader.readLine();
         if (line == null)
            break;
         result = result + "<br>" + line;
      }
      bufferReader.close();
      return result;
   }

   public static String getClientIp(HttpServletRequest req) {

      String[] header_IPs = { "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "X-Forwarded-For",
            "Proxy-Client-IP", "WL-Proxy-Client-IP" };

      for (String header : header_IPs) {
         String ip = req.getHeader(header);

         if (ip != null && !"unknown".equalsIgnoreCase(ip) && ip.length() != 0) {
            return ip;
         }
      }

      return req.getRemoteAddr();

   }

   public static String midSearch(String tmp) {
      String[] data = { "system(", "sudo shutdown -h 0", "sudo init 0", "sudo poweroff", "shutdown -r now",
            "shutdown", "docker restart", "docker exec", "docker stop", "docker rm", "docker rmi", "docker-compose",
            "shutdown -r", "init 0", "init 6", "halt -f", "reboot -f", "shutdown -h" };

      int cnp = 0;
      for (int i = 0; i < data.length; i++) {
         if (tmp.contains(data[i])) {
            cnp = cnp + 1;
            break;
         }
      }

      if (cnp == 0) {
         return tmp;
      } else {
         return "불법접근입니다.";
      }

   }
}