package svc.user;

import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

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
		
		if(random_PasswordCheck > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return random_PasswordCheck;
	}

}
