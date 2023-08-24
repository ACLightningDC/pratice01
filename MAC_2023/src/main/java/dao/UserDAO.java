package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.AddressBean;
import vo.MemberBean;

public class UserDAO {
		//필드 : 전역변수- 전체메서드에서 사용가능 
		private Connection con =null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		
		/***** 싱글톤 패턴 : DogDAo 객체 단 1 개만 생성 *********************************
		 * 이유? 외부 클래스에서 '처음 생성된 DogDAO 객체를 공유해서 사용하도록 하기 위함
		 */
		
		//1. 생성자는 무조건 private 
		private UserDAO() {}
		
		private static UserDAO userDAO;
		//
		public static UserDAO getInstance(){
			if(userDAO == null) {//DogDAO객체가 없으면
				userDAO = new UserDAO();//객체 생성
			}
			
			return userDAO;//기존 DogDAO객체의 주소 리턴
		}
		/**********************************************************************/
		
		/**
		 * 1.DB연결(Service) -> 2.sql실행 -> 3.결과처리 ->4.연결해제
		 */
		public void setConnection(Connection con){
			this.con=con;
		}
		//1.로그인 폼에서 전달된 id 와 pw로 회원여부 조회한 후 그 id 를반환
		public String selectLoginId(MemberBean user) {
			String loginId = null;
			
			String sql=" select id , password from member_table where id = ? and password = ?";
			try {
				//pstmt 객체 생성
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getPassword());
				
				//sql실행
				
				rs = pstmt.executeQuery();
				
				//결과 처리
				if(rs.next()) {
					loginId=rs.getString("id");
				}
				
				
				
				//처리
				
				
			}catch (Exception e) {
				System.out.println("selectUser 에러: " +e);//e: 예외 종류 + 예외메세지
			}finally {
				close(rs);
				close(pstmt);
			}
			
			
			return loginId;
		}

		public MemberBean selectUserInfo(String u_id) {
			MemberBean userInfo = null;
			
			String sql = "select * from member_table where id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, u_id);
				
				
				rs= pstmt.executeQuery();
				
				userInfo = new MemberBean();//기본값을
				
				if(rs.next()) {
					
					userInfo.setId(rs.getString("id"));
					userInfo.setGrade(rs.getString("grade"));
					userInfo.setName(rs.getString("name"));
					userInfo.setEmail(rs.getString("email"));
					userInfo.setPhone(rs.getString("phone"));
				}
				
				
				
			}catch(Exception e){
				System.out.println("selectUserInfo 에러 : " + e );
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return userInfo;
		}

		public int getLastMonthMoney(String id) {
			int lastMonthMoney = 0;
			
			
			
			String sql = "";
			
			try {
//				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, u_id);
//				rs= pstmt.executeQuery();
//				
//				userInfo = new MemberBean();//기본값을
//				
//				userInfo.setId(rs.getString("id"));
//				userInfo.setGrade(rs.getString("grade"));
//				userInfo.setName(rs.getString("name"));
//				userInfo.setEmail(rs.getString("email"));
//				userInfo.setPhone(rs.getString("phone"));
				
			}catch(Exception e){
				System.out.println("selectUserInfo 에러 : " + e );
			}finally {
				close(rs);
				close(pstmt);
			}
			 
			
			
			
			return lastMonthMoney;
		}


				public int gradeNORMAL(MemberBean user) {
					int gradeCount = 0;//업그레이드 성공 여부
					
					String sql="update member_table set grade='Nomal' where id=?";
					
					
					try {
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, user.getId());
						gradeCount= pstmt.executeUpdate();//성공하면 1을 리턴
						
					}catch(Exception e){
						System.out.println("downGradeNORMAL() 에러 : " + e );
					}finally {
						close(pstmt);
					}
					
					return gradeCount;
				}

				public int gradeGOLD(MemberBean user) {
						int gradeCount = 0;//업그레이드 성공 여부
						
						String sql="update member_table set grade='GOLD' where id=?";
						
						
						try {
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, user.getId());
							gradeCount= pstmt.executeUpdate();//성공하면 1을 리턴
							
						}catch(Exception e){
							System.out.println("downGradeGOLD() 에러 : " + e );
						}finally {
							close(pstmt);
						}
						
						return gradeCount;
				}

					public int gradeVIP(MemberBean user) {
						int gradeCount = 0;//업그레이드 성공 여부
						
						String sql="update member_table set grade='VIP' where id=?";
						
						
						try {
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, user.getId());
							gradeCount= pstmt.executeUpdate();//성공하면 1을 리턴
							
						}catch(Exception e){
							System.out.println("upGradeVIP() 에러 : " + e );
						}finally {
							close(pstmt);
						}
						
						return gradeCount;
					}

					public double selectSaleRate(String u_grade) {
						double saleRate = 1.0;
						
						String sql ="select salerate from grade_table where grade = ?";
						try {
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, u_grade);
							
							rs = pstmt.executeQuery();
							
							if(rs.next()) {
								saleRate = rs.getInt("salerate")/100.0;
							}
							
						}catch (Exception e) {
							System.out.println("[DAO] selectSaleRate 에러"+ e);
						}finally {
							close(con);
							close(rs);
						}
						
						return saleRate;
					}

					public int insertUser(MemberBean user) {
						int result = 0;
						//kpomdate timestamp default now()
						String sql =" insert into member_table(id, grade, password, name, email, phone) values(?,?,?,?,?,?)";
						
						//joindate timestamp
						//데이터 베이스에 now()없을때 now()추가
						try {
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, user.getId());
							pstmt.setString(2, user.getGrade());
							
							pstmt.setString(3, user.getPassword());
//							pstmt.setString(3, user.getPassword());
							
							pstmt.setString(4, user.getName());
							pstmt.setString(5, user.getEmail());
							pstmt.setString(6, user.getPhone());
							
							result = pstmt.executeUpdate();
						}catch (Exception e) {
							System.out.println("[DAP] insertUser 메소드 에러"+e );

						}finally {
							close(pstmt);

						}
						
						return result;
					}


					public int insertAddr(AddressBean addr) {
						int result = 0;
						
						String sql =" insert into address_table(id,postcode,address1,address2) values(?,?,?,?)";
						try {
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, addr.getId());
							pstmt.setInt(2, addr.getPostcode());
							pstmt.setString(3, addr.getAddress1());
							pstmt.setString(4, addr.getAddress2());
							
							result = pstmt.executeUpdate();
						}catch (Exception e) {
							System.out.println("[DAP] insertAddr 메소드 에러"+e );
						}finally {
							close(pstmt);
						}
						
						return result;
					}

					public MemberBean getUserInfo(String u_id) {
						MemberBean memberinfo = null;
						System.out.println("getUserInfo 작동");

						String sql =" select * from member_table where id = ? ";
								try {
									pstmt = con.prepareStatement(sql);
									System.out.println("getUserInfo stmt 연결");


									pstmt.setString(1, u_id);

									rs = pstmt.executeQuery();
									
									if(rs.next()) {
										memberinfo = new MemberBean();
										memberinfo.setId(rs.getString("id"));
										memberinfo.setGrade(rs.getString("grade"));
										memberinfo.setPassword(rs.getString("password"));
										memberinfo.setName(rs.getString("name"));
										memberinfo.setEmail(rs.getString("email"));
										memberinfo.setPhone(rs.getString("email"));
										memberinfo.setJoindate(rs.getString("joindate"));										
									}
									System.out.println(memberinfo.toString());
										
								}catch(Exception e) {
									System.out.println("[DAO] getUserInfo 에러" + e);
								}finally {
									close(pstmt);
									close(rs);
								}
						
						return memberinfo;
					}

					public AddressBean getAddressInfo(String u_id) {
						AddressBean addr = null;
						
						String sql=" select * from address_table where id = ?";
						try {
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, u_id);
							
							rs= pstmt.executeQuery();
							if(rs.next()) {
								addr = new AddressBean();
								addr.setId(u_id);
								addr.setPostcode(rs.getInt("postcode"));
								addr.setAddress1(rs.getString("address1"));
								addr.setAddress2(rs.getString("address2"));
							}
								
						}catch(Exception e){
							System.out.println("[DAO] getAddressInfo 에러"+e);
						}finally {
							close(pstmt);
							close(rs);
						}
					
						return addr;
					}


					public boolean IdCheck(String ck_id) {
						System.out.println("DAO IdCheck 확인");
						boolean IdCheck = false;
						
						String sql=" select id from member_table where id = ?";
						try {
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, ck_id);
							
							rs= pstmt.executeQuery();
							if(rs.next()) {
								if(rs.getString("id").equals(ck_id)) {
									IdCheck = true;
								}
							}
						}catch(Exception e){
							System.out.println("[DAO] IdCheck 에러"+e);
						}finally {
							close(pstmt);
							close(rs);
						}
					
						
						return IdCheck;
					}


					public int userUpdate(MemberBean user) {
						int UpdateCheck = 0;
						
						String sql=" update member_table set name= ?, email= ?, phone= ? where id = ?  ";
						try {
							pstmt = con.prepareStatement(sql);
							
							pstmt.setString(1, user.getName());
							pstmt.setString(2, user.getEmail());
							pstmt.setString(3, user.getPhone());
							pstmt.setString(4, user.getId());
							
							UpdateCheck = pstmt.executeUpdate();
							
						}catch(Exception e){
							System.out.println("[DAO] userupdate 에러"+e);
						}finally {
							close(pstmt);
						}
						
						
						return UpdateCheck;					
					}

					public int addrUpdate(AddressBean addr) {
							int UpdateCheck = 0;
						
						String sql=" update address_table set postcode = ? , address1 = ?, address2=? where id = ?  ";
						try {
							pstmt = con.prepareStatement(sql);
							
							pstmt.setInt(1, addr.getPostcode());
							pstmt.setString(2, addr.getAddress1());
							pstmt.setString(3, addr.getAddress2());
							pstmt.setString(4, addr.getId());
							
							UpdateCheck = pstmt.executeUpdate();
							
						}catch(Exception e){
							System.out.println("[DAO] addrupdate 에러"+e);
						}finally {
							close(pstmt);
						}
						
						
						return UpdateCheck;		
					}

					public int UserDelete(String id) {
						int deleteCheck = 0;
						
					String sql=" delete from member_table where id =? ";
					try {
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1,id);
						
						deleteCheck = pstmt.executeUpdate();
						
					}catch(Exception e){
						System.out.println("[DAO] UserDelete 에러"+e);
					}finally {
						close(pstmt);
					}
					
					System.out.println("UserDelete 실행");
					System.out.println("UserDelete 실행 값"+deleteCheck);
					return deleteCheck;							
					}

					public int addrDelete(String id) {
						System.out.println("addrDelete id 값"+id);

						int deleteCheck = 0;
						
					String sql=" delete from address_table where id = ? ";
					try {
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1,id);
						
						deleteCheck = pstmt.executeUpdate();
						
					}catch(Exception e){
						System.out.println("[DAO] AddressDelete 에러"+e);
					}finally {
						close(pstmt);
					}
					
					System.out.println("addrDelete 실행 값"+deleteCheck);
					return deleteCheck;	
					}

					public MemberBean findId(String u_email) {
						MemberBean memberinfo= null;
						String sql =" select id from member_table where email = ?";
						try {
							pstmt= con.prepareStatement(sql);
							
							pstmt.setString(1, u_email);
							rs = pstmt.executeQuery();
							
							if(rs.next()) {
								memberinfo = new MemberBean();
								memberinfo.setId(rs.getString("id"));
							}
						}catch(Exception e) {
							System.out.println("findId 에러 "+e);
						}finally {
							close(rs);
							close(pstmt);
						}
						
						return memberinfo;
					}


					public int UserRandomPasswordUpdate(String random_Password, String id, String email) {
						int Check= 0;
						
						String sql ="update member_table password = ? where id = ? and email = ?";
						
						
						return Check;
					}

}
