package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class MenuManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//관리자만 메뉴관리하도록 grade 확인 
		HttpSession session =  request.getSession();
		String a_grade = (String)session.getAttribute("a_grade");
		
		if(a_grade == null || a_grade.equalsIgnoreCase("ADMIN")) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인해주세요.');");
			out.println("location.href='adminView.jsp'");//'로그인 폼 보기'요청
			out.println("</script>");
		}else {
			request.setAttribute("showAdmin", "/admin/admin_template.jsp");
			forward = new ActionForward("adminMain.jsp", false);
		}
		
		return forward;
	}

}
