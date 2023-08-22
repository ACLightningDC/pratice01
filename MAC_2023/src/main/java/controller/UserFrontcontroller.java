package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.user.UserLoginAction;
import action.user.userJoinAction;
import action.user.userLogoutAction;
import action.user.userUpdateAction;
import action.user.userViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontcontroller
 */

//확장자가 dog이면 무조건 DogFrontController로 이동하여doProcess(request, response) 실행됨 
@WebServlet("*.usr")
public class UserFrontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserFrontcontroller() {
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
		
		request.setCharacterEncoding("utf-8");//반드시 첫줄 (post방식)
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
		
		System.out.println("[User]command : " + command);//어떤 요청인지 확인하기 위해 출력
		
		if(command.equals("/userMain.usr")) {//'index.jsp에서 userMain.jsp 뷰페이지 보기' 요청 
			//부모 인터페이스 = 구현한 자식 객체
			forward = new ActionForward("userMain.jsp" , false);
		}
		/*------------'로그인 폼 보기'------------------------------------------------------------------------*/
		else if(command.equals("/userLogin.usr")) {//로그인 폼 보기 요청하면 
			request.setAttribute("showPage", "user/loginForm.jsp");
			forward = new ActionForward("userTemplate.jsp",false);//반드시 디스패치 방식으로 
		}
		else if(command.equals("/userLoginAction.usr")) {//로그인 폼 보기 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			System.out.println("userLoginAction.usr 실행됨");
			action = new UserLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		/*------------'회원가입 폼 보기'------------------------------------------------------------------------*/
		else if(command.equals("/userJoin.usr")) {//회원가입 폼 보기 요청하면 
			request.setAttribute("showPage", "user/userJoin.jsp");
			forward = new ActionForward("userTemplate.jsp",false);//반드시 디스패치 방식으로 
		}
		else if(command.equals("/userJoinAction.usr")) {//로그인 폼 보기 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			action = new userJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		/*로그아웃 폼 보기*/ 		
		else if(command.equals("/userLogout.usr")) {//로그아웃 
			//부모 인터페이스 = 구현한 자식 객체
			action = new userLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		/*---------회원정보가 셋팅된 회원정보관리 폼보기'요청 -> 수정된 정보처리-------------------------*/
		else if(command.equals("/userView.usr")) {//로그아웃 
			//부모 인터페이스 = 구현한 자식 객체
			action = new userViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		else if(command.equals("/userUpdate.usr")) {//로그인 폼 보기 요청하면 
			//부모 인터페이스 = 구현한 자식 객체
			action = new userUpdateAction();
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



















