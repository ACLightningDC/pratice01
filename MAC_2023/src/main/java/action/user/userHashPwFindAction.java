package action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserHashPwFindService;
import vo.ActionForward;
import vo.MemberBean;

public class userHashPwFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		
		UserHashPwFindService userHashPwFindService = new UserHashPwFindService();
		MemberBean userInfo = userHashPwFindService.userHashPwFind(id , email);
		
		return forward;
	}

}
