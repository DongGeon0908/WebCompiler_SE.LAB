package linux;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBconnection.DBconnection;

@WebServlet("/shellCompile_basic")
public class shellCompile_basic extends HttpServlet {
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// utf-8 인코딩
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		// Session 받기
		HttpSession session = req.getSession(true);

		// 생성자 모임
		File log, codeTestOld;
		BufferedWriter bf;
		PrintWriter out = resp.getWriter();

		try {

			// 어떤 도커가 살아있는지 확인
			String playDocker = "";

			String docker = Reader("/usr/local/apache/docker/basic/docker.txt");
			FileWriter dockerCycle = new FileWriter("/usr/local/apache/docker/basic/docker.txt");

			ArrayList<String> dockerList = new ArrayList<String>();

			shellCmd("sh /usr/local/apache/docker/basic/check.sh");
			File check01 = new File("/usr/local/apache/docker/basic/check01.txt");
			File check02 = new File("/usr/local/apache/docker/basic/check02.txt");
			File check03 = new File("/usr/local/apache/docker/basic/check03.txt");

			if (check01.length() == 0) {
				dockerList.add("se01");
			}
			if (check02.length() == 0) {
				dockerList.add("se02");
			}
			if (check03.length() == 0) {
				dockerList.add("se03");
			}
			int length = dockerList.size();

			if (length == 1) {
				playDocker = dockerList.get(0);
				dockerCycle.write(dockerList.get(0));
				dockerCycle.close();
			} else if (length == 2) {
				if (docker.indexOf(dockerList.get(0)) != -1) {
					playDocker = dockerList.get(0);
					dockerCycle.write(dockerList.get(1));
					dockerCycle.close();
				} else if (docker.indexOf(dockerList.get(1)) != -1) {
					playDocker = dockerList.get(1);
					dockerCycle.write(dockerList.get(0));
					dockerCycle.close();
				}
			} else if (length == 3) {
				if (docker.indexOf("se01") != -1) {
					playDocker = dockerList.get(0);
					dockerCycle.write("se02");
					dockerCycle.close();
				} else if (docker.indexOf("se02") != -1) {
					playDocker = dockerList.get(1);
					dockerCycle.write("se03");
					dockerCycle.close();
				} else if (docker.indexOf("se03") != -1) {
					playDocker = dockerList.get(2);
					dockerCycle.write("se01");
					dockerCycle.close();
				} else {
					docker = "se01";
					playDocker = dockerList.get(0);
					dockerCycle.write("se02");
					dockerCycle.close();
				}
			}

			log = new File("/usr/local/apache/log/codeLog.txt");
			bf = new BufferedWriter(new FileWriter(log, true));

			String userId = (String) session.getAttribute("id");

			String code = req.getParameter("code");
			String codeType = req.getParameter("codeType");

			String[] splitCode = code.split("D3EA7KG44QW1ER0458,|D3EA7KG44QW1ER0458");// split해서 라인별로 배열에 저장
			code = "";
			for (int i = 0; i < splitCode.length; i++) {// 라인+개행문자를 통해 하나의 변수에 저장
				code += splitCode[i];
				code += "\n";
			}

			// 1차 방어막
			String wordCheck = wordCheck(code);
			if (wordCheck.equals("접근금지")) {
				out.print(wordCheck);

				Date initTime = new Date(session.getCreationTime()); // 최초 세션 생성 시각
				Date recentTime = new Date(session.getLastAccessedTime()); // 최근 세션 접근 시각
				bf.write(userId + " / " + getClientIp(req) + " / " + initTime + " / " + recentTime + " / " + playDocker
						+ " / " + codeType + " / 불법 접근입니다.   \n");

				bf.write(wordCheck + "\n");
				bf.flush();
				bf.close();

			} else {

				bf.write("user_id = " + userId + "\n");
				bf.flush();

				bf.write("언어 종류 : " + codeType + "\n");
				bf.write(code + "\n");
				bf.flush();

				if (codeType.equals("c")) {
					codeTestOld = new File("/usr/local/apache/share/codeTest");
					if (codeTestOld.exists()) {
						shellCmd("rm -r /usr/local/apache/share/codeTest");
					}

					FileWriter codew = new FileWriter("/usr/local/apache/share/codeTest.c");
					codew.write(code);
					codew.close();

					FileWriter code_c = new FileWriter("/usr/local/apache/share/code_c.sh");
					code_c.write("docker restart " + playDocker + "\n");
					code_c.write("docker exec " + playDocker + " sh -c 'cd data; gcc -o codeTest codeTest.c'");
					code_c.close();
					shellCmd("sh /usr/local/apache/share/code_c.sh"); // shellcmd로 바로 실행 불가능 그래서
					// sh파일 먼저 돌림

					FileWriter algoMid_c = new FileWriter("/usr/local/apache/share/codeMid_c.sh");
					algoMid_c.write("docker restart " + playDocker + "\n");
					algoMid_c.write("docker exec " + playDocker + " sh -c 'cd data; ./codeTest 2> error.txt'" + "\n");
					algoMid_c.write("docker stop " + playDocker + "\n");
					algoMid_c.close();

					try {
						String playResult = shellCmd("sh ./share/codeMid_c.sh"); // shellcmd로 바로 실행 불가능 그래서 sh파일 먼저
																					// 돌림
						// playResult = playResult.replace("<br>", ""); // 알고리즘 코드 실행시 추가적으로 출력되는 문자열
						playResult = playResult.replace(playDocker, ""); // 알고리즘 코드 실행시 추가적으로 출력되는 문자열

						out.print(playResult);

					} catch (Exception e) {
						out.print(Reader("/usr/local/apache/share/error.txt"));
					}
				}

				else if (codeType.equals("java")) {
					codeTestOld = new File("/usr/local/apache/share/codeTest.class");
					if (codeTestOld.exists()) {
						shellCmd("rm -r /usr/local/apache/share/codeTest.class");
					}
					FileWriter codew = new FileWriter("/usr/local/apache/share/codeTest.java");
					codew.write(code);
					codew.close();

					FileWriter code_java = new FileWriter("/usr/local/apache/share/code_java.sh");
					code_java.write("docker restart " + playDocker + "\n");
					code_java.write("docker exec " + playDocker
							+ " sh -c 'export LC_ALL=C.UTF-8; cd data; javac -encoding utf-8 codeTest.java'");
					code_java.close();
					shellCmd("sh /usr/local/apache/share/code_java.sh"); // shellcmd로 바로 실행 불가능 그래서 sh파일 먼저 돌림 --> 내부 코드

					FileWriter codeMid_java = new FileWriter("/usr/local/apache/share/codeMid_java.sh");
					codeMid_java.write("docker restart " + playDocker + "\n");
					codeMid_java.write("docker exec " + playDocker
							+ " sh -c 'cd data; java -Dfile.encoding=utf-8 codeTest 2> error.txt '" + "\n");
					codeMid_java.write("docker stop " + playDocker + "\n");
					codeMid_java.close();

					try {
						String playResult = shellCmd("sh ./share/codeMid_java.sh"); // shellcmd로 바로 실행 불가능 그래서 sh파일
																					// 먼저 돌림
						playResult = playResult.replace(playDocker, ""); // 알고리즘 코드 실행시 추가적으로 출력되는 문자열

						out.print(playResult);

					} catch (Exception e) {
						out.print(Reader("/usr/local/apache/share/error.txt"));
					}
				}

				else if (codeType.equals("python")) {
					codeTestOld = new File("/usr/local/apache/share/codeTest.py");
					if (codeTestOld.exists()) {
						shellCmd("rm -r /usr/local/apache/share/codeTest.py");
					}
					FileWriter codew = new FileWriter("/usr/local/apache/share/codeTest.py");
					codew.write("# -*- coding: utf-8 -*-\n" + code);
					codew.close();

					FileWriter codeMid_python = new FileWriter("/usr/local/apache/share/codeMid_python.sh");
					codeMid_python.write("docker restart " + playDocker + "\n");
					codeMid_python.write(
							"docker exec " + playDocker + " sh -c 'cd data; python3 codeTest.py 2> error.txt'" + "\n");
					codeMid_python.write("docker stop " + playDocker + "\n");
					codeMid_python.close();

					try {
						String playResult = shellCmd("sh ./share/codeMid_python.sh"); // shellcmd로 바로 실행 불가능 그래서
																						// sh파일 먼저 돌림
						playResult = playResult.replace(playDocker, ""); // 알고리즘 코드 실행시 추가적으로 출력되는 문자열

						out.print(playResult);

					} catch (Exception e) {
						out.print(Reader("/usr/local/apache/share/error.txt"));
					}
				}

				else if (codeType.equals("javascript"))

				{
					codeTestOld = new File("/usr/local/apache/share/codeTest.js");
					if (codeTestOld.exists()) {
						shellCmd("rm -r /usr/local/apache/share/codeTest.js");
					}
					FileWriter codew = new FileWriter("/usr/local/apache/share/codeTest.js");
					codew.write(code);
					codew.close();

					FileWriter codeMid_js = new FileWriter("/usr/local/apache/share/codeMid_js.sh");
					codeMid_js.write("docker restart " + playDocker + "\n");
					codeMid_js.write(
							"docker exec " + playDocker + " sh -c 'cd data; js codeTest.js 2> error.txt'" + "\n");
					codeMid_js.write("docker stop " + playDocker + "\n");
					codeMid_js.close();

					try {
						String playResult = shellCmd("sh ./share/codeMid_js.sh"); // shellcmd로 바로 실행 불가능 그래서 sh파일 먼저
																					// 돌림
						playResult = playResult.replace(playDocker, ""); // 알고리즘 코드 실행시 추가적으로 출력되는 문자열

						out.print(playResult);

					} catch (Exception e) {
						out.print(Reader("/usr/local/apache/share/error.txt"));
					}
				}

			}

		} catch (Exception e) {
			out.print("<strong>코드 오류</strong>");
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
		if (bufferReader.readLine() != null) {
			while ((line = bufferReader.readLine()) != null) {
				result = result + "<br>" + line;
			}
		} else
			result = "컴파일 오류";

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

	public static String wordCheck(String code) {
		String[] data = { "system(", "sudo shutdown -h 0", "sudo init 0", "sudo poweroff", "shutdown -r now",
				"shutdown", "docker restart", "docker exec", "docker stop", "docker rm", "docker rmi", "docker-compose",
				"shutdown -r", "init 0", "init 6", "halt -f", "reboot -f", "shutdown -h" };

		int dangerWord = 0;
		for (int i = 0; i < data.length; i++) {
			if (code.contains(data[i])) {
				dangerWord = dangerWord + 1;
				break;
			}
		}

		if (dangerWord == 0) {
			return code;
		} else {
			return "접근금지";
		}

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