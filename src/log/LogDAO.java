package log;

import java.util.ArrayList;
import java.io.*;

public class LogDAO {

	public ArrayList<LogVO> getLogAll() {//로그 전체 반환

		ArrayList<LogVO> list = new ArrayList<LogVO>();

		try {
			// 파일 객체 생성
			File file = new File("C:\\Users\\안병욱\\Desktop\\java\\se_editor\\log.txt");// 절대경로 사용할것
			// 입력 스트림 생성
			FileReader filereader = new FileReader(file);
			// 입력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			
			//첫 두줄 가비지 값이므로 넘기고 시작.
			line = bufReader.readLine();
			line = bufReader.readLine();
			
			while ((line = bufReader.readLine()) != null) {
				LogVO vo = new LogVO();

				String[] tmp = line.split("/");
				vo.setId(tmp[0]);
				vo.setId2(tmp[1]);
				vo.setSession1(tmp[2]);
				vo.setSession2(tmp[3]);
				vo.setContainerId(tmp[4]);
				vo.setLanguage(tmp[5]);
				vo.setResult(tmp[6]);

				list.add(vo);
			}

			bufReader.close();

			return list;
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public ArrayList<LogVO> getLogById(String id){//id값에 맞는 로그 반환
		
		ArrayList<LogVO> tmpList = new ArrayList<LogVO>(getLogAll());
		ArrayList<LogVO> list = new ArrayList<LogVO>();
		
		for(int i=0; i<tmpList.size(); i++)
			if(id.equals(tmpList.get(i).getId().replace(" ","")))
			{
				LogVO tmp = tmpList.get(i);
				
				list.add(tmp);
			}
		
		return list;
	}
	
	public ArrayList<LogVO> getLogByLanguage(String language){// 언어별로 로그 반환
		
		ArrayList<LogVO> tmpList = new ArrayList<LogVO>(getLogAll());
		ArrayList<LogVO> list = new ArrayList<LogVO>();
		
		for(int i=0; i<tmpList.size(); i++)
			if(language.equals(tmpList.get(i).getLanguage().replace(" ", "")))
			{
				LogVO tmp = tmpList.get(i);
				
				list.add(tmp);
			}
		
		return list;
	}
	
	public String changeDate(String beforeDate) { // 날짜별로 로그를 반환하기 위해 필요 형식으로 날짜를 변환
		
		String date="";
		String monthArray[]= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		
		String tmp[] = beforeDate.split("-");
		
		date+=monthArray[ Integer.parseInt(tmp[1])-1 ];
		date+="."+tmp[2];
		date+="."+tmp[0];
		
		return date;	
	}
	
	public ArrayList<LogVO> getLogByDate(String date){
		//Tue Aug 18 01:33:18 PDT 2020 형식으로 바꿔야 한다.
		// Aug nn 2020 세개만 놔두고 다 죽여야 함.
		// 자리수가 정규화 되어있음
		// SubString 으로 규칙적으로 밀면 가능할 듯?

		ArrayList<LogVO> tmpList = new ArrayList<LogVO>(getLogAll());
		ArrayList<LogVO> list = new ArrayList<LogVO>();
		
		for(int i=0; i<tmpList.size(); i++)
		{
			
		}
		
		return list;
	}
		
}
