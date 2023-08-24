package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import vo.MemberBean;

public class UserIdFindService {


	public MemberBean findId(String u_email) {
		
		Connection con = getConnection();
		UserDAO dao = UserDAO.getInstance();
		
		dao.setConnection(con);

		
		
		MemberBean userInfo= dao.findId(u_email);
		
		return userInfo;
	}

}
