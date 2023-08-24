package action.user;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserIdFindService;
import vo.ActionForward;
import vo.MemberBean;

public class userIDFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String u_email = request.getParameter("email");
		
		UserIdFindService userIdFindService = new UserIdFindService();
		MemberBean userInfo = userIdFindService.findId(u_email);
		
		if(userInfo == null ) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			
			
		}else {
			
			String u_id = userInfo.getId();
			request.setAttribute("u_id", u_id);
			
			request.setAttribute("showPage", "user/findIdComplete.jsp");
			forward = new ActionForward("userTemplate.jsp",false);
			
		}
		
		return forward;
	}

}
