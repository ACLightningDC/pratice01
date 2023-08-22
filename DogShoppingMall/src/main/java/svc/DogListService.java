package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.DogDAO;
import vo.Dog;

public class DogListService {
	//1. 필드
	
	//2. 기본생성자
	
	//3. 메서드
	public ArrayList<Dog> getDogList(){
		//1. 커넥션 풀에서 Connection 객체 얻어와
		
		Connection con = getConnection();//생략(이유?import static 하여)
		
		//2. 싱글톤 패턴 : DogDAo 객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		
		//3. DB 작업에 사용된 Connection 객체 DogDAO 에 전달하여 DB연결하여 작업하도록 서비스해줌
		dogDAO.setConnection(con);
		
		/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
		
		ArrayList<Dog> dogList = dogDAO.selectDogList();
		
		/*---------------(update,delete,insert) 성공하면 commit, 실패하면 rollback();-------
		 *           단, select 제외 ----*/
		
		//4.해제
		close(con);//JDBCUtill.생략(이유? import static 하여)
		
		return dogList;
	}
}
