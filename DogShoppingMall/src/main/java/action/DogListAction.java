package action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;

import svc.DogListService;
import vo.ActionForward;
import vo.Dog;


//개상풍목록보기 요청을 처리하는 Action클래스
public class DogListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//753p-그림의 위쪽부분 : 개상품 목록
		DogListService dogListService = new DogListService();
		ArrayList<Dog> dogList = dogListService.getDogList();
		
		//753p-그림의 아래쪽부분 : 오늘 본 개 상품 목록--------------------------
		ArrayList<String> todayImageList =  new ArrayList<String>();
		
		Cookie[] cookieArray =  request.getCookies();
		
		if(cookieArray !=null) {
			for(int i = 0 ; i<cookieArray.length; i++) {
				if(cookieArray[i].getName().startsWith("today")) {
					todayImageList.add(cookieArray[i].getValue());
				}
			}
		}
		
		/************************************************************/
		request.setAttribute("dogList", dogList);
		request.setAttribute("todayImageList", todayImageList);
		
		//[return 방법-1]
		/*
		forward = new ActionForward();//url은 null, isRedirect =false(디스패치)
		forward.setPath("dogList.jsp");//url은 "dogList.jsp" ,, isRedirect =false(디스패치)
		return forward;
		*/
		
		//[return 방법-2]
		forward = new ActionForward("dogList.jsp" , false);//디스패치 : request공유 =주소 그대로 
		return forward;
		
		//[return 방법-3]
		//return new ActionForward("dogList.jsp" , false);
	}

}
