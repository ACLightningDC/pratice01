package action.user;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.DeleteService;
import svc.user.UserHashPwChangeService;
import vo.ActionForward;

public class UserHashPwChangeAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward  = null ;
		
		String temporary_password = request.getParameter("temporary_password");
		String password = request.getParameter("password");
		
		
		HttpSession session =  request.getSession();
		String id = (String) session.getAttribute("u_id");
		
		System.out.println("action 아이디값 " +  id);
		UserHashPwChangeService userHashPwChangeService = new UserHashPwChangeService();
		int ChangeCheck = userHashPwChangeService.PwChange(id ,temporary_password , password);
		
		
		if(ChangeCheck > 0 ) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호 변경 실패')");
			out.println("history.back()");
			out.println("</script>");
			//회원가입 실패 알림창 -> 그전으로
			
			
		}else {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 변경되었습니다.');");
			out.println("location.href='userMain.usr';");//out.println("location.href='userMain')
			out.println("</script>");
			
		}
		
		return forward;
	}
}
