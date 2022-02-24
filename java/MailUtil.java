package kr.co.ttcnc.common.util;

import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class MailUtil {
	 
	private static String mailTemplete = ""
			+ "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
			+ "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0\">\n"
			+ "	<link rel=\"stylesheet\" href=\"http://nana.band/template/style/reset.css\">\n"
			+ "	<link rel=\"stylesheet\" href=\"http://nana.band/template/style/page.css\">\n"
			+ "	<link rel=\"stylesheet\" href=\"http://nana.band/template/style/animation.css\">\n"
			+ "	<link rel=\"stylesheet\" href=\"http://nana.band/template/style/theme.css\">\n"
			+ "	<link rel=\"stylesheet\" href=\"/cmn/bootstrap-3.3.6/css/bootstrap.css\" />\n"
			+ "	<link rel=\"stylesheet\" href=\"/cmn/css/da-form.css\" />\n"
			+ "	<link rel=\"stylesheet\" href=\"/cmn/css/dev.css\" />\n" + "	<style>\n"
			+ "		.e-mail-form .gra {\n" + "			font-size: 32px;\n"
			+ "			font-family: 'NanumMyeongjo', '나눔명조', serif;\n" + "			font-weight: 500;\n"
			+ "			line-height: 1.2;\n"
			+ "			background: -webkit-linear-gradient(75deg, #47aaea, #15d2cb);\n"
			+ "			background: -o-linear-gradient(75deg, #47aaea, #15d2cb);\n"
			+ "			background: -moz-linear-gradient(75deg, #47aaea, #15d2cb);\n"
			+ "			background: linear-gradient(75deg, #47aaea, #15d2cb);\n"
			+ "			-webkit-background-clip: text;\n" + "			-webkit-text-fill-color: transparent;\n"
			+ "		}\n" + "	</style>\n" + "</head>\n" + "\n" + "<body>\n" + "<!-- Contents -->\n"
			+ "<div class=\"e-mail-form\" style=\"width: 100%;height: auto;box-sizing: border-box;text-align: center;font-family: 'spns', Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, Noto Sans CJK SC, WenQuanYi Micro Hei, Arial,sans-serif\">\n"
			+ "	<div style=\"width: 100%;max-width: 880px;height: auto;margin: auto;padding: 33px\">\n"
			+ "		<div class=\"header\" style=\"width: 100%;text-align: left;\">\n"
			+ "			<div class=\"logo\" style=\"background: url(${logo}) no-repeat;width: 50px;height: 33px;background-size: 100%;display: inline-block;\"></div>\n"
	//		+ "			<div class=\"tit\" style=\"display: inline-block;vertical-align: top;font-size: 19px;font-weight: bold;padding-left:-20px;padding-top: 6px;width: auto;\">TTC&C 안내 메일</div>\n"
			+ "		</div>\n"
			+ "		<div class=\"mail-content\" style=\"border: 10px solid #509fff;box-sizing: border-box;padding: 40px;\">\n"
			+"			<div style=\"font-size: 16px;color: #4d4d4d;padding: 35px 0px 5px 0px;text-align: left;\">\n"
			+ "${body}"
			+ "		<div class=\"footer\" style=\"background-color: #509fff;box-sizing: border-box;padding: 10px 10px 15px 10px;font-size: 14px;color: #fff;line-height: 1.4;\">\n"
			+ "			본 메일은 정보통신망 이용촉진 및 정보보호법률에 의거한 회원님의 수신동의 여부에 따라 발송된 발신전용<br />\n"
			+ "			메일입니다. 메일을 원하지 않으시거나 문의 사항이 있으시면 관리자에게 문의하시기 바랍니다.<br />\n" + "			<br />\n"
			+ "			${addr}<br />\n"
			+ "			ⓒ Copyright©TTC&C Corp. All Right Reserved.<br />\n" + "		</div>\n" + "	</div>\n"
			+ "</div>\n" + "<!--// Contents -->\n" + "\n" + "</body>\n" + "</html>";

	private static String userId;
    private static String password;

    @Value("${gmail.userId}")
    public void setUserId(String id) {
      this.userId = id;
    }
    @Value("${gmail.password}")
    public void setPassword(String id) {
      this.password = id;
    }
    
    public static String getUserId() {
        return userId;
    }

    public static String getPassword() {
        return password;
    }
    
    /**
      
     * @param mailMap 
             * mailTo : 수신자
             * subject : 제목
             * body : 내용  (html 형태나 text)
               mailFrom : 발신자 ( 지정 안할경우 계정으로 진행 )
               logo : 로고 이미지
               addr : 회사주소
     * @return
     */
	public static boolean sendMail(Map<String, Object> mailMap)  {
	  /*
	  * 라이브러리 : 
	      'com.sun.mail:javax.mail:1.6.2'
	      'javax.mail:javax.mail-api:1.6.2'
	    계정 설정
	     1.IMAP 액세스 사용
	     https://mail.google.com/mail/u/0/?hl=ko#settings/fwdandpop
	     2.보안수준이 낮은 앱의 액세스 허용
	     https://myaccount.google.com/lesssecureapps?rapt=AEjHL4PmsLN7fBj087OyBlb6CUm4caU4hzkrYF1bh5No00nSSDuvses9eYS1Okius__21Vs6V1b8U7rjd5mqaURj4GD19YRXLg
	  */ 
		    Properties props = new Properties();  
		    String host = "smtp.gmail.com";  
		  //587포트        
		    props.put("mail.smtp.host", host); //SMTP Host  
		    props.put("mail.smtp.port", "587"); //TLS Port  
		    props.put("mail.smtp.auth", "true"); //enable authentication  
		    props.put("mail.smtp.starttls.enable", "true"); //enable   
		    props.put("mail.smtp.ssl.trust", host);   
		    //465포트  (YamlUtil.Gmail )
		    /*Properties props= new Properties();        
		    props.put("mail.smtp.host", host); 
		    props.put("mail.smtp.port", "465"); 
		    props.put("mail.smtp.starttls.enable","true"); 
		    props.put("mail.smtp.auth", "true"); 
		    props.put("mail.smtp.debug", "true"); 
		    props.put("mail.smtp.socketFactory.port", "465"); 
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
		    props.put("mail.smtp.socketFactory.fallback", "false");*/  
		       
	        Session session = Session.getInstance(props,  new javax.mail.Authenticator() { 
	        protected PasswordAuthentication getPasswordAuthentication() { 
	        	return new PasswordAuthentication( getUserId() , getPassword() );   
	        }}); 
	        try{
	        	
	        	String  addr = (String) mailMap.get("addr");
	        	String  logo = (String) mailMap.get("logo"); 
	        	String strTemplate = mailTemplete.replace("${logo}", CommonUtil.null2Str(logo, ""))
	        				.replace("${addr}",CommonUtil.null2Str(addr, ""));
	        	
	            MimeMessage message = new MimeMessage(session); 
	            message.setFrom(new InternetAddress( CommonUtil.null2Str(mailMap.get("mailFrom"),getUserId())));// 
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(CommonUtil.null2Str(mailMap.get("mailTo"),""))); 
	            message.setSubject(CommonUtil.null2Str(mailMap.get("subject"),""));
	            String body = strTemplate.replace("${body}", CommonUtil.null2Str(mailMap.get("body"), ""));
	            String type = CommonUtil.null2Str(mailMap.get("type"), "html");
	           // message.setText(body); 
	            
	            if (body != null) { // 본문 설정
	    			if ("html".equals(type))
	    				message.setContent(body, "text/html; charset=UTF-8");
	    			else
	    				message.setContent(body, "text/plain");
	    		}
	            Transport transport = session.getTransport("smtp");  
	            transport.connect(getUserId(),getPassword());
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	            
	        } catch(Exception e){
	            e.printStackTrace();
	        } 
	     return true;   
	}
	
}
