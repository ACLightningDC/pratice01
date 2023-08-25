package action.user;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.DeleteService;
import svc.user.UserHashPwChangeService;
import util.SHA256;
import vo.ActionForward;

public class UserHashPwChangeAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward  = null ;
		
		String temporary_password = SHA256.encodeSHA256(request.getParameter("pre_password"));
		String password = SHA256.encodeSHA256(request.getParameter("new_password"));
		
		
		System.out.println("발급된 임시 비밀번호"+ temporary_password);
		System.out.println("비밀번호"+ password);
		
		HttpSession session =  request.getSession();
		String id = (String) session.getAttribute("u_id");
		
		System.out.println("action 아이디값 " +  id);
		UserHashPwChangeService userHashPwChangeService = new UserHashPwChangeService();
		int ChangeCheck = userHashPwChangeService.PwChange(id ,temporary_password , password);
		
		
		if(!(ChangeCheck > 0 )) {
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
			
			//방법-2
			forward = new ActionForward("userMain.usr",true);
		}
		
		return forward;
	}
}
