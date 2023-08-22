/**
 * DB 작업 시 공통적으로 사용하는 메서드를 정의
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	//'커넥션 풀'에서 Connection 객체 얻어와 받환
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQLDB");//context name 값 
			con = ds.getConnection();
			
			con.setAutoCommit(false);//Connection 객체에 트랜젝션을 완성하지 못하도록 false로 설정
			
			
		}catch(Exception e) {
			System.out.println("JdbcUtil 클래스의 getConnection()예외 = " + e);
		}
	
	return con;
	}
	
	/*
	 * Connection 객체를 닫아주는 메서드
	 */
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ResultSet 객체를 닫아주는 메서드
	 */
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ResultSet 객체를 닫아주는 메서드
	 */
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*--------------------------------------------------------------*/
	/**
	 * 트랜잭션 중에 실행된 작업들을 완료시키는 메서드
	 * insert, delete , update 한 후 commit함
	 */
	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("commit success");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 트랜잭션 중에 실행된 작업들을 '취소'시키는 메서드
	 * insert, delete ,update '실패' 한 후 rollback함
	 */
	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback success");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}


