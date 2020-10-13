import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class shellOrder {
	// 리눅스에게 명령보내기, 출력 데이터 가져오기
		public static String shellCmd(String command) throws Exception {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(command);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String result = br.readLine();

			return result;
		}
}
