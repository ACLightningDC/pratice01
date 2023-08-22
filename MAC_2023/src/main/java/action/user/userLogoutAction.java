package action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class userLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		/**
		 * session 영역의 속성들을 제거
		 */
		HttpSession session = request.getSession();
		//session.invalidate();//세션의 모든 속성을 삭제 
		
		session.removeAttribute("u_id");
		session.removeAttribute("u_password");
		session.removeAttribute("u_grade");
		session.removeAttribute("u_name");
		session.removeAttribute("u_email");
		
		forward= new ActionForward("userMain.jsp",true);
		return forward;
	}

}
