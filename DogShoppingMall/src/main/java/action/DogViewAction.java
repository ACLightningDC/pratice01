package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		
		DogViewService dogViewService = new DogViewService();
		int id = Integer.parseInt(request.getParameter("id"));
		Dog dogView = dogViewService.getDogView(id);
		
		request.setAttribute("dogView", dogView);
		
		//753p:2번째 그림 하단부분(dogList.jsp): 오늘 본 개 상품 목록
		Cookie todayImageCookie = new Cookie("today"+id, dogView.getImage());//"today","pu.jpg"
		todayImageCookie.setMaxAge(24*60*60);//24시간
		response.addCookie(todayImageCookie);//반드시 , 응답에 쿠키객체를 추가하여 클라이언트로 보낸다.
		
		
		
		
		forward = new ActionForward("dogView.jsp" , false); 
		return forward;
		
		//return new ActionForward("dogView.jsp" , false); //디스패치 방법
	}

}
