package log;

import java.util.ArrayList;
import java.io.*;

public class LogDAO {

	public ArrayList<LogVO> getLogAll() {//�α� ��ü ��ȯ

		ArrayList<LogVO> list = new ArrayList<LogVO>();

		try {
			// ���� ��ü ����
			File file = new File("C:\\Users\\�Ⱥ���\\Desktop\\java\\se_editor\\log.txt");// ������ ����Ұ�
			// �Է� ��Ʈ�� ����
			FileReader filereader = new FileReader(file);
			// �Է� ���� ����
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			
			//ù ���� ������ ���̹Ƿ� �ѱ�� ����.
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
	
	public ArrayList<LogVO> getLogById(String id){//id���� �´� �α� ��ȯ
		
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
	
	public ArrayList<LogVO> getLogByLanguage(String language){// ���� �α� ��ȯ
		
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
	
	public String changeDate(String beforeDate) { // ��¥���� �α׸� ��ȯ�ϱ� ���� �ʿ� �������� ��¥�� ��ȯ
		
		String date="";
		String monthArray[]= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		
		String tmp[] = beforeDate.split("-");
		
		date+=monthArray[ Integer.parseInt(tmp[1])-1 ];
		date+="."+tmp[2];
		date+="."+tmp[0];
		
		return date;	
	}
	
	public ArrayList<LogVO> getLogByDate(String date){
		//Tue Aug 18 01:33:18 PDT 2020 �������� �ٲ�� �Ѵ�.
		// Aug nn 2020 ������ ���ΰ� �� �׿��� ��.
		// �ڸ����� ����ȭ �Ǿ�����
		// SubString ���� ��Ģ������ �и� ������ ��?

		ArrayList<LogVO> tmpList = new ArrayList<LogVO>(getLogAll());
		ArrayList<LogVO> list = new ArrayList<LogVO>();
		
		for(int i=0; i<tmpList.size(); i++)
		{
			
		}
		
		return list;
	}
		
}
