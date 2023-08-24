package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.IsIdCheckService;
import vo.ActionForward;

public class idCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward= null;
		
		String ck_id = request.getParameter("idck");
		
		IsIdCheckService isIdCheckService = new IsIdCheckService();
		boolean isIdCheck = isIdCheckService.IdCheck(ck_id);
		
		
		System.out.println(isIdCheck +" idCheckAction isIdCheck 확인");
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out= response.getWriter();
		
		if(isIdCheck) {

			System.out.println("true 실행");

			out.println("<script type='text/javascript'>");
			out.println("alert('아이디 확인');");
			out.println("opener.document.getElementById('id').value = ck_id; ");
			out.println("self.close();");
			out.println("</script>");
			
		}else {

			System.out.println("else 실행");
			
			out.println("<script type='text/javascript'>");
			out.println("alert('중복된 아이디입니다.');");
			out.println("</script>");
		}
		
		System.out.println("idCheckAction 실행");
		
		forward = new ActionForward("../userJoin.jsp",false);
		return forward;
	}

}
