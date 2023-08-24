package action.user;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.DeleteService;
import vo.ActionForward;

public class UserDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward  =null;
		
		HttpSession session =  request.getSession();
		String id = (String) session.getAttribute("u_id");
		
		System.out.println("action 아이디값 " +  id);
	//String id = request.getAttribute("user_id");
		DeleteService DeleteService = new DeleteService();
		boolean DeleteCheck = DeleteService .Delete(id);
		
		
		if(DeleteCheck == false ) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			//회원가입 실패 알림창 -> 그전으로
			
			
		}else {
//			response.setContentType("text/html;charset=UTF-8");
//			
//			//회원가입 성공 알림창 -> 로그인 폼보기 요청(리다이렉트 방식)
			session = request.getSession();
			
			session.removeAttribute("u_id");
			session.removeAttribute("u_password");
			session.removeAttribute("u_grade");
			session.removeAttribute("u_name");
			session.removeAttribute("u_email");

			session.removeAttribute("cartList");//장바구니
			session.removeAttribute("totoalMoney");//총금액
			session.removeAttribute("saleTotalMoney");//세일된 총금액ㄴ
			session.removeAttribute("lastestOrder");//최근주문
			
			
			String cookie = request.getHeader("Cookie");
			
			if(cookie!=null) {
				Cookie[] cookies =request.getCookies();
				for(int i =0 ; i < cookies.length ; i++) {
					String cookieName = cookies[i].getName();
					Cookie cookiech = null;
					if(cookieName.equals("u_id")) {
						
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
					}
					if(cookieName.equals("checkbox")){
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
					}
				}
			}
//			쿠키 삭제
//			Cookie cookieU_id = new Cookie("u_id", "");
//			cookieU_id.setMaxAge(0);
//			
//			response.addCookie(cookieU_id);
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원탈퇴 성공. 그동안 감사했습니다.');");
			out.println("location.href='userMain.usr';");//out.println("location.href='userMain')
			out.println("</script>");
			
		}
		
		return forward;
	}

}
