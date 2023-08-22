package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {
	//3-1 id로 장바구니에 담을 개상품에 정보를 dog 객체에 담아 리턴 
	public Dog getCartDog(int id){
		
		Connection con = getConnection();
		
		DogDAO dogDAO = DogDAO.getInstance();
		
		dogDAO.setConnection(con);
		
		/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
		
		Dog dog = dogDAO.selectDog(id);
		
		
		/*---------------(update,delete,insert) 성공하면 commit, 실패하면 rollback();-------
		 *           단, select 제외 ----*/
		
		close(con);//JDBCUtill.생략(이유? import static 하여)
		
		return dog;
	}
	//3-2 위에서 얻어온 Dog 객체를 Session 영역의 장바구니 항목("cartList")에 추가하는 것으로 
	//		db 작업하는 것이 아니므로 1~4 필요없음
	public void addCart(HttpServletRequest request, Dog cartDog){
		HttpSession session = request.getSession();
		
		ArrayList<Cart> cartList =(ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		//★★순서 중요
//		1. 기존에 장바구니에 같은 개상품이 있으면(같은 id = 아이디가 있으면) 수량을 증가
		boolean isNewCart= true;//지금 장바구니에 담는 항목이 새로 추가되는 항목인지를 물어보는 변수
		for(int i = 0;i < cartList.size();i++) {
			
			Cart cart = cartList.get(i);
			
			if(cart.getId() == cartDog.getId())
			{	
				isNewCart = false;//장바구니 항목에 존재하면 false 로 만들어서 2.부분의 실행을 막기위해서 
				cart.setQty(cart.getQty()+1);//qty값이 2라면 : 2+1 = 3을 다시	qty에 저장
				break;
			}
		}
		
//		2. 장바구니에 같은 개상품이 없으면 
		if(isNewCart == true){
			Cart cart = new Cart();//cart객체를 생성하여(기본값으로 채워짐)
			
			//위에서 얻어온 개정보로 채워서
			cart.setId(cartDog.getId());
			cart.setKind(cartDog.getKind());
			cart.setImage(cartDog.getImage());
			cart.setPrice(cartDog.getPrice());
			
			cart.setQty(1);//이때 , 수량은 처음이므로 1로 셋팅
			
			cartList.add(cart);//장바구니에 담기			
		}
	}

}


















