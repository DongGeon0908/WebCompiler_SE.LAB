package ocr;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 리눅스 한글 패치가 필요한 상황!
@WebServlet("/ocrCompile")
public class ocrCompile extends HttpServlet {

	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();

		String fileName = req.getParameter("fileName");

		try {
			File check1 = new File("/usr/local/apache/docker/ocr/test.txt");

			if (check1.length() != 0) {
				shellCmd("rm -r /usr/local/apache/ocr/test.txt");
			}
			shellCmd("rm -r /usr/local/apache/share/" + fileName);
			shellCmd("mv " + fileName + " /usr/local/apache/share");

			FileWriter ocrFirst = new FileWriter("/usr/local/apache/docker/ocr/ocrFirst.sh");
			ocrFirst.write("docker exec se01 sh -c \"cd data; tesseract " + fileName + " test\"");
			ocrFirst.close();

			shellCmd("docker restart se01");
			shellCmd("sh /usr/local/apache/docker/ocr/ocrFirst.sh");

			File ocrResult = new File("/usr/local/apache/share/test.txt");

			String ocrCode = "";
			String logResult = null;
			if (ocrResult.length() != 0) {
				ocrCode = Reader("/usr/local/apache/share/test.txt");
				// out.print("<h2>Result</h2>");
				logResult = "success";
			} else {
				ocrCode = Reader("/usr/local/apache/share/test.txt");
				// out.print("<h2>Error</h2>");
				logResult = "fail";
			}
			String imgUrl = (String) req.getParameter("fileName");

			FileWriter pw = new FileWriter("/usr/local/apache/ocr/log.txt", true);
			HttpSession session = req.getSession();
			String user_id = (String) session.getAttribute("id"); // 회원 아이디
			Date initTime = new Date(session.getCreationTime()); // 최초 세션 생성 시각
			Date recentTime = new Date(session.getLastAccessedTime()); // 최근 세션 접근 시각
			pw.write(user_id + " / " + getClientIp(req) + " / " + initTime + " / " + recentTime + " / se01"
					+ " / ocr / " + logResult + "\n");
			pw.close();

			shellCmd("rm -r /usr/local/apache/" + fileName);
			String path = session.getServletContext().getRealPath("/");

			req.setAttribute("imgUrl", imgUrl);
			req.setAttribute("ocrCode", ocrCode);
			
			
			ServletContext context = getServletContext();
			
			RequestDispatcher dispatcher = context.getRequestDispatcher("/ide/ocr.jsp");
			//out.print("fucking God them");
			dispatcher.forward(req, resp);
			

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

}