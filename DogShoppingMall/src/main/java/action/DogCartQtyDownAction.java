package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartQtyDownService;
import svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		//[구분-1] : id로 개상품 구분 (권장 -----------------------------
		String id = request.getParameter("id");
		
//		int qty = Integer.parseInt(request.getParameter("qty")); 
//		PrintWriter out =  response.getWriter();
//		
//		if(qty == 1 ) {
//			out.println("<script>");
//			out.println("alert('더이상 감소시킬수 없습니다.');");	
//			out.println("history.back();");	
//			out.println("</script>");
//		}else {
//			DogCartQtyDownService dogCartQtyDownService  = new DogCartQtyDownService();
//			//[구분-1]
//			dogCartQtyDownService.DownCartQty(request, id);
//		}

		
		//[구분-2] : kind로 개상품 구분 
		//String kind =request.getParameter("kind");
		DogCartQtyDownService dogCartQtyDownService  = new DogCartQtyDownService();
		//[구분-1]
		dogCartQtyDownService.DownCartQty(request, id);
		
		//[구분-2]
		//dogCartQtyUpService.upCartQty(request, kind);
		
		//장바구니 해당 항모긔 수량증가 후 '장바구니 목록보기' 요청을 리다이렉트 방식으로
		forward= new ActionForward("dogCartList.dog", true);
		return forward;
	}
	
//	private ActionForward linkExecute(HttpServletRequest request, HttpServletResponse response) {
//		ActionForward forward= null;
//		
//		String id = request.getParameter("id");
//		int qty = Integer.parseInt(request.getParameter("qty")); 
//		PrintWriter out =  response.getWriter();
//		if(qty == 1 ) {
//			
//			out.println("<script>");
//			out.println("alert('더이상 감소시킬수 없습니다.');");	
//			out.println("history.back();");	
//			out.println("</script>");
//		}else {
//			DogCartQtyDownService dogCartQtyDownService  = new DogCartQtyDownService();
//			//[구분-1]
//			dogCartQtyDownService.DownCartQty(request, id);
//			forward= new ActionForward("dogCartList.dog", true);
//
//		}
//	}

}
