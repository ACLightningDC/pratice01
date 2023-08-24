package svc.user;


import java.sql.Connection;

import dao.UserDAO;

import static db.JdbcUtil.*;

public class DeleteService {

	public boolean Delete(String id) {
		System.out.println("deleteService id 값" + id);
		Connection con = getConnection();//생략(이유?import static 하여)
		
		UserDAO dao = UserDAO.getInstance();
		
		dao.setConnection(con);
		
		int userDeleteCheck = dao.UserDelete(id);
		int addrDeleteCheck = dao.addrDelete(id);
		
		boolean isUpdateResult = false;

		if(userDeleteCheck > 0 && addrDeleteCheck > 0 ) {
			isUpdateResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		return isUpdateResult;
	}

}
