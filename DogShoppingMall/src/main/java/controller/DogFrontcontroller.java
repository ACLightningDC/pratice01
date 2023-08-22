package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.DogCartAddAction;
import action.DogCartListAction;
import action.DogCartQtyDownAction;
import action.DogCartQtyUpAction;
import action.DogCartRemoveAction;
import action.DogCartSearchAction;
import action.DogListAction;
import action.DogRegisterFormAction;
import action.DogViewAction;
import action.DogRegistAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontcontroller
 */

//확장자가 dog이면 무조건 DogFrontController로 이동하여doProcess(request, response) 실행됨 
@WebServlet("*.dog")
public class DogFrontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogFrontcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");//post방식
		response.setContentType("text/html; charset=UTF-8");
		/**
		 * 1. 전송된 요청 파악
		 * 
		 * URL =  내부 프로젝트 경로 url -> http://localhost:8090/project/dogList.dog"
		 * URI ="/project/dogList.dog"
		 * contextPath="/project"
		 * 
		 * URI - contextPath = "/dogList.dog"
		 * subString()으로
		 */
		//
		
		String requestURI = request.getRequestURI();// "/project/dogList.dog"
		String contextPath = request.getContextPath();// "/project"
		String command= requestURI.substring(contextPath.length());// "/dogList.dog"
		/*********************************************/
		//2. 처리 각 요청별로 비지니스로직 호출하여 처리
		Action action = null;
		ActionForward forward =null;
		if(command.equals("/dogList.dog")) {//'개 상품목록보기 ' 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			action = new DogListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/dogView.dog")) {//'개 상품목록보기 ' 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			action = new DogViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//[관리자 모드] 
		else if(command.equals("/dogRegisterForm.dog")) {//'개 상품목록보기 ' 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			action = new DogRegisterFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//특별한 로직이 필요없고 뷰페이지만 포워딩하면 되므로
			//포워딩 방식? 리다이렉트(새요청)이든 디스패치(기존요청)이든 관계없으므로
			//forward = new ActionForward("dogRegisterForm.jsp", false);//true, false 둘다 사용가능 
		}
		/*--------------------------------------------------------*/
		else if(command.equals("/dogRegist.dog")) {//'개 상품등록 ' 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			action = new DogRegistAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartAdd.dog")) {//'선택한 개정보를 장바구니 담기 ' 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			action = new DogCartAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartList.dog")) {//'장바구니 목록보기' 요청하면
			//부모 인터페이스 = 구현한 자식 객체
			action = new DogCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartQtyup.dog")) {//'장바구니 목록보기' 요청하면
			//부모 인터페이스 = 구현한 자식 객체
			action = new DogCartQtyUpAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartQtydown.dog")) {//'장바구니 목록보기' 요청하면

			action = new DogCartQtyDownAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartSearch.dog")) {
			action = new DogCartSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartSearch.dog")) {
			action = new DogCartSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartRemove.dog")) {
			action = new DogCartRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**********************************************
		 * 3. 포워딩
		 * ********************************************/
		if(forward != null) {
			if(forward.isRedirect()) {//isRedirect==false : 디스패치 방식 isRedirect==ture 리다이렉트 방식
				  response.sendRedirect(forward.getPath());//"dogList.jsp"
			}else {//디스패치 : "dogList.jsp"에서 기존 request 공유
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
		
	}

}



















