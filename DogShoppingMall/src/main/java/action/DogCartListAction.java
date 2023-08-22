package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartListService;
import vo.ActionForward;
import vo.Cart;

public class DogCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		DogCartListService dogCartListService= new DogCartListService();
		ArrayList<Cart> cartList = dogCartListService.getCartList(request);
		//request.setAttribute("cartList", cartList);
		
		/*---장바구니 목록에 존재하는 전체 상품을 구매하는데 필요한" 총금액 계산"--------------*/
		int totalMoney = 0;
		
		for(int i = 0;i< cartList.size();i++) {
			Cart cart= cartList.get(i);
			totalMoney += cart.getPrice() *cart.getQty();
		}
		
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("cartList", cartList);
		
		forward = new ActionForward("dogCartList.jsp", false);
		return forward;
	}

}
