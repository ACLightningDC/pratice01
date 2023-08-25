package action.user;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserGradeService;
import svc.user.UserLoginService;
import util.SHA256;
import vo.ActionForward;
import vo.MemberBean;

public class UserLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		
		String u_id = request.getParameter("id");
		String u_password = request.getParameter("password");
		
		String checkbox = request.getParameter("checkbox");//아이디 체크 여부
		
		//dto=vo
		
		MemberBean user = new MemberBean();//기본값
		user.setId(u_id);
		user.setPassword(SHA256.encodeSHA256(u_password));//암호화 방법 1
		
		UserLoginService userLoginService  = new UserLoginService();
		boolean isLoginSuccess = userLoginService.login(user);
		
		if(!isLoginSuccess) {//로그인 실패하면 
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')");
			out.println("location.href='userLogin.usr'");//로그인 폼보기 요청			
			out.println("</script>");			
		}else {//로그인 성공하면
			
			Cookie cookie = new Cookie("u_id", u_id);
			System.out.println("u_id의 Cookie 객체 생성");
			
			if(checkbox !=null) {
				response.addCookie(cookie);//반드식 response객체에 추가하여 클라이언트로 보냄
			}else {
				cookie.setMaxAge(0);//쿠키 즉시삭제 -1 세션이 끝날때 삭제 
				response.addCookie(cookie);
			}
			
			//입력한 id로 회원정보를 가져와(이유? session영역에 공유)
			MemberBean userInfo = userLoginService.getUserInfo(u_id);
			
			//"지난달 구매금액"을 조회하여 사용자 등급 변경한 사용자정보를 다시 리턴받아
			userInfo = UserGradeService.updateGrade(userInfo);
			
			/* 로그인에 성공하면
			 * session영역에 속성으로 저장하여 공유
			 * u_id, u_password, U_grade, u_name, u_email
			 */
			

				HttpSession session =  request.getSession(); 
				session.setAttribute("u_id", u_id);
				
				session.setAttribute("u_password", userInfo.getPassword());
				
				//등급 필요한 이유? 구매할 때 등급으로 세일비율을 얻어올 수 있으므로(예)NORMAL:0, GOLD:0.05 VIP 0.1
				session.setAttribute("u_grade",userInfo.getGrade());
				
				session.setAttribute("u_name", userInfo.getName());
				session.setAttribute("u_email",userInfo.getEmail());
				
				session.setMaxInactiveInterval(1*60*60);//세션 유지시간을 1시간(=3600초 )으로 설정
				

			forward = new ActionForward("userMain.jsp",true);
			}
			
		
		
		
		return forward;
	}

}
