package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;
import util.SHA256;
import vo.MemberBean;

public class UserHashPwFindService {

	public MemberBean userHashPwFind(String id, String email) {
		
		Connection con = getConnection();
		
		UserDAO userDAO = UserDAO.getInstance();
		
		userDAO.setConnection(con);
		
		
		
		String random_Password = SHA256.getRandomPassword(8);
		
		int random_PasswordCheck = userDAO.UserRandomPasswordUpdate(random_Password , id , email);
		
		
		
		return null;
	}

}
