import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/sub")
public class shellCompile_python extends HttpServlet {

	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		String input = req.getParameter("code");

		out.print("<html><head><title>Send Test</title></head>");
		out.print("<body>");
		try {
			File pythonTestOld = new File("pythonTest.py");
			if (pythonTestOld.exists()) {
				shellCmd("rm -r pythonTest.py");
			}

			File pythonErrorOld = new File("pythonError.txt");
			if (pythonErrorOld.exists()) {
				shellCmd("rm -r pythonError.txt");
			}

			FileWriter pythonTest = new FileWriter("pythonTest.txt");
			pythonTest.write(input);
			pythonTest.close();

			shellCmd("cp pythonTest.txt pythonTest.py");

			String result = shellCmd("./pythonMid");
			File pythonErrorNew = new File("pythonError.txt");
			if (pythonErrorNew.exists()) {
				FileReader fileReader = new FileReader(pythonErrorNew);
				BufferedReader bufferReader = new BufferedReader(fileReader);
				List<String> errorList = new ArrayList<>();
				String line = "";
				while ((line = bufferReader.readLine()) != null) {
					errorList.add(line + "<br>");
				}

				FileWriter pythonResult = new FileWriter("pythonResult.txt");

				if (errorList.size() > 0) {
					for (int i = 0; i < errorList.size(); i++) {
						out.print(errorList.get(i));
					}
				} else {
					out.print("<h5>" + result + "</h5>");
					pythonResult.write(result);
					pythonResult.close();
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