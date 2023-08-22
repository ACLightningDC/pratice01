package svc.user;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.UserDAO;
import vo.MemberBean;

public class UserGradeService {

	public static MemberBean updateGrade(MemberBean user) {
		
		Connection con = getConnection();//생략(이유?import static 하여)
		
		//2. 싱글톤 패턴 : UserDAo 객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		
		//3. DB 작업에 사용된 Connection 객체 DogDAO 에 전달하여 DB연결하여 작업하도록 서비스해줌
		userDAO.setConnection(con);
		
		/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
		
		int lastMonthMoney = userDAO.getLastMonthMoney(user.getId());
		//현재등급 
		String grade = user.getGrade();//사용자의 현재등급 얻어와(이유? 등급과 지날달 구매금액으로 등급조정 위해)
		
		
		
//		int gradeCount = 0;
//		if(lastMonthMoney < 50000) {//지난달 구매금액이 5만원보다 작으면
//		//등급을 Nomal로 다운그ㅐ이드
//		gradeCount = userDAO.gradeNORMAL(user);				
//		}else if(lastMonthMoney >= 50000 && lastMonthMoney < 100000) {
//			gradeCount = userDAO.gradeGOLD(user);
//		}else if(lastMonthMoney >= 100000) {
//			gradeCount = userDAO.gradeVIP(user);
//
//		}
//		
//		if(gradeCount > 0) {
//			commit(con);
//		}else {
//			rollback(con);
//		}
		
		int gradeCount = 0;
		String now_grade = user.getGrade();//현재등급을 얻어와
		
		//현재등급이 NORMAL이고
		if(now_grade.equalsIgnoreCase("NORMAL")) {
			if(lastMonthMoney < 100000) {//지난달 구매금액이 10만원보다 작으면 GOLD
				gradeCount = userDAO.gradeNORMAL(user);
			}else {
				gradeCount = userDAO.gradeGOLD(user);
			}
		}
		//현재등급이 GOLD이고
		else if(now_grade.equalsIgnoreCase("GOLD")) {
			if(lastMonthMoney < 50000) {//지난달 구매금액이 10만원보다 작으면 GOLD
				gradeCount = userDAO.gradeNORMAL(user);
			}else if(50000<= lastMonthMoney && lastMonthMoney <100000){
				gradeCount = userDAO.gradeGOLD(user);
			}
			else{
				gradeCount = userDAO.gradeVIP(user);
			}
		}
		//현재등급이 VIP이고
		else if(now_grade.equalsIgnoreCase("VIP")) {
			if(lastMonthMoney < 100000) {//지난달 구매금액이 10만원보다 작으면 GOLD
				gradeCount = userDAO.gradeNORMAL(user);
			}else {//지난달 구매금액이 10만원 보다 이상이면 VIP 유지
				gradeCount = userDAO.gradeGOLD(user);
			}
		}
		
		if(gradeCount > 0) {
		commit(con);
	}else {
		rollback(con);
	}
		
//		if(grade.trim().equalsIgnoreCase("NORMAL")) {
//			
//			if(lastMonthMoney >= 50000 && lastMonthMoney <= 100000) {//지난달 구매금액이 5만원 10만원 사이면
//				gradeCount = userDAO.upGradeGOLD(user);				
//			}else if(lastMonthMoney >= 100000) {
//				gradeCount = userDAO.upGradeVIP(user);
//			}
//			//등급을 GOLD로 업그레이드
//			if(gradeCount > 0) {
//				commit(con);
//			}else {
//				rollback(con);
//			}
//		}
//
//		else if(grade.trim().equalsIgnoreCase("GOLD")) {
//			
//			if(lastMonthMoney < 50000) {//지난달 구매금액이 5만원보다 작으면
//				//등급을 Nomal로 다운그ㅐ이드
//				gradeCount = userDAO.downGradeNORMAL(user);				
//			}else if(lastMonthMoney >= 50000 && lastMonthMoney < 100000) {
//				gradeCount = userDAO.upGradeGOLD(user);
//			}else if(lastMonthMoney >= 100000) {
//			gradeCount = userDAO.upGradeVIP(user);
//			}
//			//등급을 GOLD로 업그레이드
//			if(gradeCount > 0) {
//				commit(con);
//			}else {
//				rollback(con);
//			}
//		}
//		
//		else if(grade.trim().equalsIgnoreCase("GOLD")) {
//			
//			if(lastMonthMoney < 50000) {//지난달 구매금액이 5만원보다 작으면
//				//등급을 Nomal로 다운그ㅐ이드
//				gradeCount = userDAO.downGradeNORMAL(user);				
//			}else if(lastMonthMoney >= 50000) {
//				gradeCount = userDAO.upGradeGOLD(user);
//			}else if(lastMonthMoney >= 100000) {
//				gradeCount = userDAO.upGradeVIP(user);
//			}
//			//등급을 GOLD로 업그레이드
//			if(gradeCount > 0) {
//				commit(con);
//			}else {
//				rollback(con);
//			}
//		}
			
		//수정된 사용자정보를 id 로 다시 조회하여 리턴 
		MemberBean userInfo = userDAO.selectUserInfo(user.getId());
		//등급이 NORMAL이면서 지난달 구매금액이 5만원 이상이면 
		
		//4.해제
		close(con);//JDBCUtill.생략(이유? import static 하여)
		
		return userInfo;
	}

	public double getSaleRate(String u_grade) {
		Connection con = getConnection();//생략(이유?import static 하여)
		
		//2. 싱글톤 패턴 : UserDAo 객체 생성
		UserDAO userDAO = UserDAO.getInstance();
		
		//3. DB 작업에 사용된 Connection 객체 DogDAO 에 전달하여 DB연결하여 작업하도록 서비스해줌
		userDAO.setConnection(con);
		
		/*------------- DogDAO의 해당 메서드를 호출하여 처리 ---------------------*/
		double saleRate = 1.0;
		saleRate = userDAO.selectSaleRate(u_grade); 
		
		//4.해제
		close(con);//JDBCUtill.생략(이유? import static 하여)
		
		return saleRate;
	}
	
}
