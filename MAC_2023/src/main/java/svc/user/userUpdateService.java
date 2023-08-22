package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;

public class userUpdateService {

	public static boolean userUpdate() {
		
		Connection con = getConnection();
		
		UserDAO dao = UserDAO.getInstance();
		
		dao.setConnection(con);
		
		boolean updatecheck = dao.userUpdate();
		return false;
	}

	
}
