package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import vo.AddressBean;
import vo.MemberBean;

public class UserViewService {

	public MemberBean getUserInfo(String u_id) {
		
		Connection con = getConnection();
		
		MemberBean memberbean= null;
		
		UserDAO dao = UserDAO.getInstance();
		
		MemberBean userInfo = dao.getUserInfo(u_id);
		
		return null;
	}

	public AddressBean getAddressinfo(String u_id) {
		Connection con = getConnection();
		
		MemberBean memberbean= null;
		
		UserDAO dao = UserDAO.getInstance();
		
		AddressBean userInfo = dao.getAddressInfo(u_id);
		
		return null;
	}
	 
}
