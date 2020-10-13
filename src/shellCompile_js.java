import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sub")
public class shellCompile_js extends HttpServlet {

	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		String input = req.getParameter("code");

		out.print("<html><head><title>Send Test</title></head>");
		out.print("<body>");
		try {
			File jsTestOld = new File("jsTest.js");
			if (jsTestOld.exists()) {
				shellCmd("rm -r jsTest.js");
			}

			File jsErrorOld = new File("jsError.txt");
			if (jsErrorOld.exists()) {
				shellCmd("rm -r jsError.txt");
			}

			FileWriter jsTest = new FileWriter("jsTest.txt");
			jsTest.write(input);
			jsTest.close();

			shellCmd("cp jsTest.txt jsTest.js");

			String result = shellCmd("./jsMid");
			File jsErrorNew = new File("jsError.txt");
			if (jsErrorNew.exists()) {
				FileReader fileReader = new FileReader(jsErrorNew);
				BufferedReader bufferReader = new BufferedReader(fileReader);
				List<String> errorList = new ArrayList<>();
				String line = "";
				while ((line = bufferReader.readLine()) != null) {
					errorList.add(line + "<br>");
				}

				FileWriter jsResult = new FileWriter("jsResult.txt");

				if (errorList.size() > 0) {
					for (int i = 0; i < errorList.size(); i++) {
						out.print(errorList.get(i));
					}
				} else {
					out.print("<h5>" + result + "</h5>");
					jsResult.write(result);
					jsResult.close();
				}
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

}