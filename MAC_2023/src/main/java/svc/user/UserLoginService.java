package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;


import dao.UserDAO;
import vo.MemberBean;

public class UserLoginService {

	//3. 메서드
	//1. 회원 여부
	public boolean login(MemberBean user){
		//1. 커넥션 풀에서 Connection 객체 얻어와
		
		Connection con = getConnection();//생략(이유?import static 하여)
		
		//2. 싱글톤 패턴 : UserDAo 객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		
		//3. DB 작업에 사용된 Connection 객체 DogDAO 에 전달하여 DB연결하여 작업하도록 서비스해줌
		userDAO.setConnection(con);
		
		/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
		
		String loginId = userDAO.selectLoginId(user);
		
		boolean isLoginResult = false;
		
		
		/*---------------(update,delete,insert) 성공하면 commit, 실패하면 rollback();-------
		 *           단, select 제외 ----*/
		if (loginId !=null) {
			isLoginResult = true;
		}
		
		//4.해제
		close(con);//JDBCUtill.생략(이유? import static 하여)
		
		return isLoginResult;
	}

	public MemberBean getUserInfo(String u_id) {
		//1. 커넥션 풀에서 Connection 객체 얻어와
		
		Connection con = getConnection();//생략(이유?import static 하여)
		
		//2. 싱글톤 패턴 : UserDAo 객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		
		//3. DB 작업에 사용된 Connection 객체 DogDAO 에 전달하여 DB연결하여 작업하도록 서비스해줌
		userDAO.setConnection(con);
		
		/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
		
		MemberBean userInfo = userDAO.selectUserInfo(u_id);
		
		//4.해제
		close(con);//JDBCUtill.생략(이유? import static 하여)
		
		return userInfo;
	}
	
}





