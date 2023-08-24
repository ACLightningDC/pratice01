package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import vo.AddressBean;
import vo.MemberBean;

public class UserViewService {

	public MemberBean getUserInfo(String u_id) {
		
		Connection con = getConnection();
		UserDAO dao = UserDAO.getInstance();
		
		dao.setConnection(con);

		
		
		MemberBean userInfo = dao.getUserInfo(u_id);
		
		return userInfo;
	}

	public AddressBean getAddressinfo(String u_id) {
		Connection con = getConnection();
		UserDAO dao = UserDAO.getInstance();
		
		dao.setConnection(con);

		
		
		AddressBean userInfo = dao.getAddressInfo(u_id);
		
		return userInfo;
	}
	 
}
