package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class DogRegisterFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		//포워딩 방식?
		forward = new ActionForward("dogRegisterForm.jsp", false);
		
		return forward;
	}

}
