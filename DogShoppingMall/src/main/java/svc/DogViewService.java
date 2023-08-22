package svc;

//import static db.JdbcUtil.close;
//import static db.JdbcUtil.getConnection;
//import static db.JdbcUtil.commit;
//import static db.JdbcUtil.rollback;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import vo.Dog;

public class DogViewService {

	public Dog getDogView(int id) {
		//1. 커넥션 풀에서 Connection 객체 얻어와
		
				Connection con = getConnection();//생략(이유?import static 하여)
				
				//2. 싱글톤 패턴 : DogDAo 객체 생성
				DogDAO dogDAO = DogDAO.getInstance();
				
				//3. DB 작업에 사용된 Connection 객체 DogDAO 에 전달하여 DB연결하여 작업하도록 서비스해줌
				dogDAO.setConnection(con);
				
				/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
				//순서주의 : 먼저 , 해당 개 상품의 '조회수 1 증가'
				int updateCount = dogDAO.updateReadcount(id);
				
				
				/*---------------(update,delete,insert) 성공하면 commit, 실패하면 rollback();-------
				 *           단, select 제외 ----*/
				if(updateCount > 0) {
					commit(con);
				}else {
					rollback(con);
				}
				
				//아이디로 개상품정보 조회
				Dog dogView = dogDAO.selectDog(id);
				//4.해제
				close(con);//JDBCUtill.생략(이유? import static 하여)
				
				return dogView;
	}

}
