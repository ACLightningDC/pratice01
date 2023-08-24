package svc.user;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.UserDAO;

public class UserHashPwChangeService {

	public int PwChange(String id , String temporary_password ,String password) {
		
		int PwChangeCheck = 0;
		
		Connection con = getConnection();//생략(이유?import static 하여)
		
		UserDAO userDAO = UserDAO.getInstance();
		
		userDAO.setConnection(con);
		
		PwChangeCheck = userDAO.UserHashPwChange(id  , temporary_password , password);
		
		return PwChangeCheck;
	}

}
