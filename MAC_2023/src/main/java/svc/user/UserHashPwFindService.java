package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import util.SHA256;
import vo.MemberBean;

public class UserHashPwFindService {

	public int userHashPwFind(String random_Password, String id, String email) {
		
		Connection con = getConnection();
		
		UserDAO userDAO = UserDAO.getInstance();
		
		userDAO.setConnection(con);
		
		
		
		
		int random_PasswordCheck = userDAO.UserRandomPasswordUpdate(random_Password , id , email);
		
		
		
		return random_PasswordCheck;
	}

}
