package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserUpdateService;
import vo.ActionForward;
import vo.AddressBean;
import vo.MemberBean;

public class UserUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward  =null;
		
		HttpSession session =  request.getSession();
		String id = (String) session.getAttribute("user_id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		int postcode = Integer.parseInt(request.getParameter("postcode")); 
		String address1 = request.getParameter("address1");
		String address2= request.getParameter("address2");	
		
		MemberBean member = new MemberBean();
		member.setId(id);
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		AddressBean addr = new AddressBean(id , postcode, address1,address2);
		
		UserUpdateService userUpdateservice = new UserUpdateService();
		boolean userUpdateCheck = UserUpdateService.userUpdate(member , addr);
		
		if(userUpdateCheck == false) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원수정 실패')");
			out.println("history.back()");
			out.println("</script>");
			//회원가입 실패 알림창 -> 그전으로
			
			out.close();
			
			//[방법-1] 회원수정 성공 알림창 -> "userMain.user'요청 (리다이렉트 방식)
			
		}else {
//			response.setContentType("text/html;charset=UTF-8");
//			
//			//회원가입 성공 알림창 -> 로그인 폼보기 요청(리다이렉트 방식)
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원수정 성공')");
			out.println("location.href= 'userMain.usr';");
			out.println("</script>");
			
			out.close();
			
			session.setAttribute("u_name", name);
			session.setAttribute("u_email", email);
			
			request.setAttribute("showPage", "user/userUpdateComplete.jsp");
			forward = new ActionForward("userTemplate.usr", true);
		}
		
		return forward;
		
		
	}

}
