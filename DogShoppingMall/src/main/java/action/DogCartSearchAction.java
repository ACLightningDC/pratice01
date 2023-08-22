package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int startMoney = Integer.parseInt(request.getParameter("statrtMoney"));
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));
		
		DogCartSearchService dogCartSearchService = new DogCartSearchService();
		ArrayList<Cart> cartList = dogCartSearchService.dogCartSearch(startMoney , endMoney , request);
		
		//최하 ~최고 가격으로 검색시 장바구니에 없는 가격으로 검색하거나 잘못 검색(예: 최하 3000~ 최고 1000) 하면
		if(cartList.isEmpty()) {
			//[방법-1]session 영역에 바인딩
			HttpSession session = request.getSession();
			session.setAttribute("sarchCartList", cartList);
			
			//방법-2 request 영역에 바인딩
			//request.setAttribute("searchCartList", searchCartList);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			out.println("<script>");
			out.println("alert('없음 ');");
			out.println("location.href='dogCartList.jsp?request=request';");
			out.println("</script>");
			
			out.close();
		}
		int totalMoney = 0;
		
		for(int i = 0;i< cartList.size();i++) {
			Cart cart= cartList.get(i);
			totalMoney += cart.getPrice() *cart.getQty();
		}
		request.setAttribute("totalMoney", totalMoney);
		
		request.setAttribute("cartList", cartList); //주의 반드시 "cartList"
		request.setAttribute("startMoney", startMoney);
		request.setAttribute("endMoney", endMoney);
		
		
		//★★ 주의 : 디스패치 방식 -기존 request 그대로 북페이제에서 공유하도록
		forward = new ActionForward("dogCartList.jsp", false);
		return forward;
	}

}
