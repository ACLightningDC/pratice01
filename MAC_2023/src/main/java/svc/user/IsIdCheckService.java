package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;

public class IsIdCheckService {

	public boolean IdCheck(String ck_id) {
			System.out.println("IdCheck 확인");
		Connection con = getConnection();
		
		UserDAO userDAO = UserDAO.getInstance();
		
		userDAO.setConnection(con);
		
		boolean IdCheckResult = false;
		System.out.println("IdCheck 확인"+ IdCheckResult);

		IdCheckResult = userDAO.IdCheck(ck_id);

	
		
		return IdCheckResult;
	}
	
}
