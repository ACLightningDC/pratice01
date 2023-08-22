package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		//[구분-1] : id로 개상품 구분 (권장 -----------------------------
		String id = request.getParameter("id");
		
		//[구분-2] : kind로 개상품 구분 
		//String kind =request.getParameter("kind");
		
		DogCartQtyUpService dogCartQtyUpService  = new DogCartQtyUpService();
		//[구분-1]
		dogCartQtyUpService.upCartQty(request, id);
		//[구분-2]
		//dogCartQtyUpService.upCartQty(request, kind);
		
		//장바구니 해당 항모긔 수량증가 후 '장바구니 목록보기' 요청을 리다이렉트 방식으로
		//session 영역의 장바구니("cartList") 속성의 수량값을 감소시켰으므로 변경된 장바구니("cartList"를 다시 새요청으로)
		forward= new ActionForward("dogCartList.dog", true);
		return forward;
	}

}
