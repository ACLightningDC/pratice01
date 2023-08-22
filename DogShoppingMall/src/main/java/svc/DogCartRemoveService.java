package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartRemoveService {

	public void dogCartRemove(HttpServletRequest request, String[] removeArray) {
		
		HttpSession session =  request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		System.out.println(cartList.toString());
		
//		for(int i = 0 ; i<removeArray.length; i++) {
//			
//			for(int j = 0 ; j<cartList.size() ; j++){
//				
//				if(cartList.get(j).getId() == Integer.parseInt(removeArray[i])){
//					cartList.remove(cartList.get(j));	
//				}
//			}
//		}
		
		for(int i = 0 ; i<removeArray.length; i++) {
			
			for(int j = 0 ; j<cartList.size() ; j++){
				
				if(cartList.get(j).getKind().equals((removeArray[i]))){
					cartList.remove(cartList.get(j));
				}
			}
		}
		
	}

}

