package svc.user;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.UserDAO;
import vo.AddressBean;
import vo.MemberBean;

public class UserUpdateService {

	public static boolean userUpdate(MemberBean mem , AddressBean addr) {
		
		Connection con = getConnection();
		
		UserDAO dao = UserDAO.getInstance();
		
		dao.setConnection(con);
		
		int updateMemberCheck = dao.userUpdate(mem);
		
		int updateAddressCheck = dao.addrUpdate(addr);
		boolean isUpdateResult = false;

		if(updateMemberCheck > 0 && updateAddressCheck > 0 ) {
			isUpdateResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		return isUpdateResult;
	}

	
}
