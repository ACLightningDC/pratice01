package action.user;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserHashPwFindService;
import util.SHA256;
import vo.ActionForward;

public class userHashPwFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		String random_Password = SHA256.getRandomPassword(8);
		String random_PasswordEncode = SHA256.encodeSHA256(random_Password);
		UserHashPwFindService userHashPwFindService = new UserHashPwFindService();
		int Check = userHashPwFindService.userHashPwFind(random_PasswordEncode , id , email);
		
		if(Check > 0) {
			
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			String sender = "0523mytop@gmail.com"; //보내는사람
			String receiver = email; //받는사람 : 1명
			String subject = id + "님의 임시비밀번호 입니다."; //제목
			String content = id + "님의 임시비밀번호는" + random_Password +"입니다."; //내용
			content += "<a href='#'>로그인 하러 가기</a>"; //내용
			
			String host = "smtp.gmail.com";
			
			final String username = "0523mytop";
			final String password = "uypdshsuoppdvsgs" ;
			
			final int port=587;
			
			try {
				Properties properties=  System.getProperties();
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.port", port);
				
				Session session= Session.getDefaultInstance(properties, //SMTP 서버 정보
						new Authenticator() { //사용자인증정보객체: Authenticator추상클래스 생성자()정의와 생성을 동시에 
							//재정의 해야 할 메서드
							@Override
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);//사용자인증접보 객체 리턴 
							} //사용자 인증정보
						}//생성자 끝
					);
				
				Message message = new MimeMessage(session);			
				Address sender_address = new InternetAddress(sender);
				Address receiver_address = new InternetAddress(receiver);
				message.setHeader("content-type","text/html;charset=utf-8");
				message.setFrom(sender_address);//보내는 메일주소 셋팅
				message.addRecipient(Message.RecipientType.TO, receiver_address);
				message.setSubject(subject);
				message.setContent(content, "text/html;charset=utf-8");
				message.setSentDate(new java.util.Date());
				
				Transport.send(message);
				
				out.println("<h3>메일이 정상적으로 전송되었습니다.</h3>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			request.setAttribute("Password", random_PasswordEncode);
		request.setAttribute("showPage", "user/hash/findHashPwComplete.jsp");
		forward = new ActionForward("userTemplate.jsp",false);
		
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('임시 비밀번호 발급에 문제가 발생했습니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
