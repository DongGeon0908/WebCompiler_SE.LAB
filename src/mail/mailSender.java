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
	// ���� ���ڵ�
    final String bodyEncoding = "UTF-8"; //������ ���ڵ�
    
    String subject = "SE HomePage ��й�ȣ ã��";// ����
    String fromEmail = "hhjjkk7186@gamil.com";//������ �ּ�
    String fromUsername = "SE HomePage Manager";// �������� �̸�
    String toEmail = vo.getMail(); // �޴� �ּ�
    
    final String username = "hhjjkk7186@gmail.com";   // gmail id      
    final String password = "hoq159dks"; // gmail pw
    
    // ���Ͽ� ����� �ؽ�Ʈ
    String url="http://localhost:8080/se_editor/changePwForm.jsp?id="+vo.getId();
    StringBuffer sb = new StringBuffer();
    sb.append("<h3>SE_HOMEPAGE�Դϴ�.</h3>\n");
    sb.append("<h4>�Ʒ��� ��ũ�� �̵��Ͽ� ��й�ȣ�� �������ּ���</h4>\n");
    sb.append("<form action=\""+url+"\" name='id' method='post'>");//form post�� �̿��Ͽ� data�� ������ �ʰ���
    sb.append("<input type='submit' value='��й�ȣ ����'>");
    sb.append("</form>");
    
    String html = sb.toString();
    
    // ���� �ɼ� ����
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
      // ���� ����  ���� ���� ����
      Authenticator auth = new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      };
      
      // ���� ���� ����
      Session session = Session.getInstance(props, auth);
      
      // ���� ��/���� �ɼ� ����
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(fromEmail, fromUsername));
      message.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
      message.setSubject(subject);
      message.setSentDate(new Date());
      
      // ���� ������ ����
      Multipart mParts = new MimeMultipart();
      MimeBodyPart mTextPart = new MimeBodyPart();
      MimeBodyPart mFilePart = null;
 
      // ���� ������ - ����
      mTextPart.setText(html, bodyEncoding, "html");
      mParts.addBodyPart(mTextPart);
            
      // ���� ������ ����
      message.setContent(mParts);
      
      // MIME Ÿ�� ����
      MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
      MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
      MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
      MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
      MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
      MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
      CommandMap.setDefaultCommandMap(MailcapCmdMap);
 
      // ���� �߼�
      Transport.send( message );
      result = true;
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return result;
  }
}