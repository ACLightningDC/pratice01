package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import svc.DogCartRemoveService;
import vo.ActionForward;
import vo.Cart;

public class DogCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
				
		String [] removeArray = request.getParameterValues("remove");
		System.out.println(removeArray.toString());
		DogCartRemoveService dogCartRemoveService = new DogCartRemoveService();
		dogCartRemoveService.dogCartRemove(request , removeArray);		
		
		forward = new ActionForward("dogCartList.dog",true);
		return forward;
	}

}
