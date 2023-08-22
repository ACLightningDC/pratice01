package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import db.JdbcUtil;
import vo.Dog;

public class DogDAO {
	//필드 : 전역변수- 전체메서드에서 사용가능 
	private Connection con =null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/***** 싱글톤 패턴 : DogDAo 객체 단 1 개만 생성 *********************************
	 * 이유? 외부 클래스에서 '처음 생성된 DogDAO 객체를 공유해서 사용하도록 하기 위함
	 */
	
	//1. 생성자는 무조건 private 
	private DogDAO() {}
	
	private static DogDAO dogDAO;
	//
	public static DogDAO getInstance(){
		if(dogDAO == null) {//DogDAO객체가 없으면
			dogDAO = new DogDAO();//객체 생성
		}
		
		return dogDAO;//기존 DogDAO객체의 주소 리턴
	}
	/**********************************************************************/
	
	/**
	 * 1.DB연결(Service) -> 2.sql실행 -> 3.결과처리 ->4.연결해제
	 */
	public void setConnection(Connection con){
		this.con=con;
	}
//1. 모든 개 상품정보를 조회하여  ArrayList<Dog>객체 반환 
	public ArrayList<Dog> selectDogList() {
		//[방법-1]
		//ArrayList<Dog> dogList = new ArrayList<Dog>();
		//[방법 -2 ]권장
		ArrayList<Dog> dogList = null;
		
		//sql
		String sql = "select * from dog";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			/*
			while(rs.next()) {
				Dog dog = new Dog();
				
				//조회된 dog값으로
				dog.setId(rs.getInt("id"));
				dog.setKind(rs.getString("kind"));;
				dog.setCountry(rs.getString("country"));
				dog.setPrice(rs.getInt("price"));
				dog.setHeight(rs.getInt("height"));
				dog.setWeight(rs.getInt("weight"));
				dog.setContent(rs.getString("content"));
				dog.setImage(rs.getString("image"));
				dog.setReadcount(rs.getInt("readcount"));
				
				dogList.add(dog);
			}
			*/
			
			if(rs.next()) {
				dogList = new ArrayList<Dog>();//기본값으로 채워진 Dog 객체를
				
				do {
					Dog dog = new Dog();
					/*
					//조회된 dog값으로
					dog.setId(rs.getInt("id"));
					dog.setKind(rs.getString("kind"));;
					dog.setCountry(rs.getString("country"));
					dog.setPrice(rs.getInt("price"));
					dog.setHeight(rs.getInt("height"));
					dog.setWeight(rs.getInt("weight"));
					dog.setContent(rs.getString("content"));
					dog.setImage(rs.getString("image"));
					dog.setReadcount(rs.getInt("readcount"));
					
					dogList.add(dog);
					*/
					
					dogList.add(new Dog(rs.getInt("id"),
							rs.getString("kind"),
							rs.getString("country"),
							rs.getInt("price"),
							rs.getInt("height"),
							rs.getInt("weight"),
							rs.getString("content"),
							rs.getString("image"),
							rs.getInt("readcount")
							)//생성자 괄호
						);//add 괄호
				} while (rs.next());
			}//if 문 끝 
		}catch(Exception e) {
			System.out.println("selectDogList 에러: " +e);//e: 예외 종류 + 예외메세지
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
		
		return dogList;
	}
	public int updateReadcount(int id) {
		int updateCount = 0;
		
		//sql
		//[방법 - 1]
		String sql = "update dog set readcount = readcount + 1 where id = ?";
		
		//[방법 -2] : int id
//		String sql = "update dog SET readcount=readcount+1 where id='"+id+"'";
		
//		String sql = "update dog SET readcount=readcount+1 where id="+id;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			updateCount = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("updateReadcount 에러: " +e);//e: 예외 종류 + 예외메세지
		}finally {
			JdbcUtil.close(pstmt);
		}
		// TODO 자동 생성된 메소드 스텁
		return updateCount;
	}
	public Dog selectDog(int id) {
		Dog dog = null;
		
		String sql = "select * from dog where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					dog = new Dog(rs.getInt("id"),
							rs.getString("kind"),
							rs.getString("country"),
							rs.getInt("price"),
							rs.getInt("height"),
							rs.getInt("weight"),
							rs.getString("content"),
							rs.getString("image"),
							rs.getInt("readcount"));
			}//if 문 끝 
		}catch(Exception e){
			System.out.println("selectDogView 에러: " +e);//e: 예외 종류 + 예외메세지
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return dog;
	}
	public int insertDog(Dog dog) {
		System.out.println(dog.toString());
		int insertCount  = 0;
		String sql = " insert into dog values(dog_seq.nextval,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dog.getKind());
			pstmt.setString(2, dog.getCountry());
			pstmt.setInt(3, dog.getPrice());
			pstmt.setInt(4, dog.getHeight());
			pstmt.setInt(5, dog.getWeight());
			pstmt.setString(6, dog.getContent());
			pstmt.setString(7, dog.getImage());
			pstmt.setInt(8, dog.getReadcount());
			
			insertCount = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("insertDog 에러: " +e);//e: 예외 종류 + 예외메세지
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return insertCount ;
	}
}


