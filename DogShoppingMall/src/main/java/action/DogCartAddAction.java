package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartAddService;
import vo.ActionForward;
import vo.Dog;

public class DogCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		
		DogCartAddService dogCartAddService = new DogCartAddService();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Dog dogCart = dogCartAddService.getCartDog(id);
		
		//2.
		dogCartAddService.addCart(request,dogCart );
		
		//'장바구니 목록보기' 요청을 리다이렉트 방식으로 포워딩
		forward = new ActionForward("dogCartList.dog", false);
		return forward;
	}

}
