package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartListService {
	//1. 필드
	
	//2. 기본생성자
	
	//3. 메서드
	public ArrayList<Cart> getCartList(HttpServletRequest request ){
		HttpSession session =  request.getSession();
		ArrayList<Cart>  cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		return cartList;
	}
}
