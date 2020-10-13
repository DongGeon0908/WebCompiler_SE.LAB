package mail;

import java.util.Date;
import java.util.Properties;
 
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import member.*;


public class mailSender {

  public static boolean sendMail(memberVO vo) {
    boolean result = false;
	// 메일 인코딩
    final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩
    
    String subject = "SE HomePage 비밀번호 찾기";// 제목
    String fromEmail = "hhjjkk7186@gamil.com";//보내는 주소
    String fromUsername = "SE HomePage Manager";// 보내는이 이릅
    String toEmail = vo.getMail(); // 받는 주소
    
    final String username = "hhjjkk7186@gmail.com";   // gmail id      
    final String password = "hoq159dks"; // gmail pw
    
    // 메일에 출력할 텍스트
    String url="http://localhost:8080/se_editor/changePwForm.jsp?id="+vo.getId();
    StringBuffer sb = new StringBuffer();
    sb.append("<h3>SE_HOMEPAGE입니다.</h3>\n");
    sb.append("<h4>아래의 링크로 이동하여 비밀번호를 변경해주세요</h4>\n");
    sb.append("<form action=\""+url+"\" name='id' method='post'>");//form post를 이용하여 data를 보이지 않게함
    sb.append("<input type='submit' value='비밀번호 변경'>");
    sb.append("</form>");
    
    String html = sb.toString();
    
    // 메일 옵션 설정
    Properties props = new Properties();    
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "465");
    props.put("mail.smtp.auth", "true");
 
    props.put("mail.smtp.quitwait", "false");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");
    
    try {
      // 메일 서버  인증 계정 설정
      Authenticator auth = new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      };
      
      // 메일 세션 생성
      Session session = Session.getInstance(props, auth);
      
      // 메일 송/수신 옵션 설정
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(fromEmail, fromUsername));
      message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
      message.setSubject(subject);
      message.setSentDate(new Date());
      
      // 메일 콘텐츠 설정
      Multipart mParts = new MimeMultipart();
      MimeBodyPart mTextPart = new MimeBodyPart();
      MimeBodyPart mFilePart = null;
 
      // 메일 콘텐츠 - 내용
      mTextPart.setText(html, bodyEncoding, "html");
      mParts.addBodyPart(mTextPart);
            
      // 메일 콘텐츠 설정
      message.setContent(mParts);
      
      // MIME 타입 설정
      MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
      MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
      MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
      MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
      MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
      MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
      CommandMap.setDefaultCommandMap(MailcapCmdMap);
 
      // 메일 발송
      Transport.send( message );
      result = true;
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return result;
  }
}