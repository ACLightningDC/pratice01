package action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.userUpdateService;
import vo.ActionForward;

public class userUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.getAttribute("");
		
		
		
		userUpdateService useupdateservice = new userUpdateService();
		boolean userupdate = userUpdateService.userUpdate();
		
		return null;
	}

}
