import java.io.*;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 리눅스 한글 패치가 필요한 상황!
//@WebServlet("/sub")
public class shellCompile_java extends HttpServlet {

	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		PrintWriter out = resp.getWriter();
		String input = req.getParameter("code");

		out.print("<html><head><title>Send Test</title></head>");
		out.print("<body>");

		try {
			File javaTestOld = new File("SELAB.class");
			if (javaTestOld.exists()) {
				shellCmd("rm -r SELAB.class");
			}

			File javaError = new File("javaError.txt");
			if (javaError.exists()) {
				shellCmd("rm -r javaError.txt");
			}

			FileWriter javaTestText = new FileWriter("javaTest.txt");
			javaTestText.write(input);
			javaTestText.close();
			shellCmd("./javaMid");

			File javaTestNew = new File("SELAB.class");
			FileWriter javaResult = new FileWriter("javaResult.txt");

			if (javaTestNew.exists()) {
				String result = shellCmd("java SELAB");
				out.print("<h2>Result</h2>");
				out.print("<h5>" + result + "</h5>");
				javaResult.write(result);
				javaResult.close();
			} else {
				String error = Reader("javaError.txt");
				out.print("<h2>Error</h2>");
				out.print("<h5>" + error + "</h5>");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.print("<textarea cols=\"40\" rows=\"30\">" + input + "</textarea>");
		out.print("<h3><a href='example.jsp'>이전 페이지</a></h3>");
		out.print("</body></html>");

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

}