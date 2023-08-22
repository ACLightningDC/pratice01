package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.user.UserJoinService;
import vo.ActionForward;
import vo.AddressBean;
import vo.MemberBean;

public class userJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String grade = request.getParameter("grade");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		int postcode = Integer.parseInt(request.getParameter("postcode")); 
		String address1 = request.getParameter("address1");
		String address2= request.getParameter("address2");
		
		
		//매개변수가 없는 생성자 : 암호화 방법-1
//		MemberBean user = new MemberBean();
//		user.setId(id);
//		user.setGrade(grade);
//		user.setPasswordSHA256(password);
//		user.setName(name);
//		user.setEmail(email);
//		user.setPhone(phone);
		
		//매개변수가 있는 생성자 : 암호화 방법-2
		MemberBean user = new MemberBean(id ,grade,password,name,email,phone);
		AddressBean addr = new AddressBean(id, postcode, address1, address2);
		
		UserJoinService userJoinService = new UserJoinService();
		boolean isUserJoinSuccess = userJoinService.userJoin(user, addr);
		
		if(isUserJoinSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패')");
			out.println("history.back()");
			out.println("</script>");
			//회원가입 실패 알림창 -> 그전으로
			
		}else {
//			response.setContentType("text/html;charset=UTF-8");
//			
//			//회원가입 성공 알림창 -> 로그인 폼보기 요청(리다이렉트 방식)
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('회원가입 성공')");
//			out.println("location.href='userLogin.usr'");
//			out.println("</script>");
			
			forward = new ActionForward("userLogin.usr", true);
		}
		
		return forward;
	}

}
