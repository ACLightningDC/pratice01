package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogRegistService {

	public boolean registDog(Dog dog) {
		
		
		//1. 커넥션 풀에서 Connection 객체 얻어와
		
		Connection con = getConnection();//생략(이유?import static 하여)
				
				//2. 싱글톤 패턴 : DogDAo 객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
				
				//3. DB 작업에 사용된 Connection 객체 DogDAO 에 전달하여 DB연결하여 작업하도록 서비스해줌
		dogDAO.setConnection(con);
				
				/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
		boolean isRegistSucess =false;
		
		int insertCount = dogDAO.insertDog(dog);
		//4.해제
		
		if(insertCount >0) {
			commit(con);
			isRegistSucess=true;
		}else {
			rollback(con);
		}
		
		close(con);//JDBCUtill.생략(이유? import static 하여)
		
		return isRegistSucess;
	}
}
