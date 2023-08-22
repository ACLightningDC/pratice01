package action.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.user.UserViewService;
import vo.ActionForward;
import vo.AddressBean;
import vo.MemberBean;

public class userViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		
		
		String u_id = (String) session.getAttribute("u_id");
		
		if(u_id == null) {//로그인 상태가 아니면
			PrintWriter out= response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.');");
			out.println("location.href='userLogin.usr'");
			out.println("</script>");
			
		}else {//로그인 상태
			
			UserViewService userViewService = new UserViewService();
			
			MemberBean userInfo = userViewService.getUserInfo(u_id);
			AddressBean userAddrInfo = userViewService.getAddressinfo(u_id);
					
				request.setAttribute("user",userInfo );
				request.setAttribute("addr",userAddrInfo );
				request.setAttribute("showPage", "user/userView.jsp");
			
			forward = new ActionForward("userTemplate.jsp", false);//반드시 디스패치 방식으로 표시
		}
		
		return forward;
	}

}
