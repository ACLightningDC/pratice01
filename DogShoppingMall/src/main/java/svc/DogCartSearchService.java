package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartSearchService {

	public ArrayList<Cart> dogCartSearch(int startMoney ,int endMoney ,HttpServletRequest request) {
		
		
		HttpSession session =  request.getSession();
		
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		ArrayList<Cart> cartListSearch = new ArrayList<Cart>();
		
		for(int i = 0 ;i<cartList.size(); i++) {
			if(startMoney <= cartList.get(i).getPrice() && cartList.get(i).getPrice() <= endMoney) {
				cartListSearch.add(cartList.get(i));
			}
		}
		
		
		
		return cartListSearch;

		
	}

	
	
}
