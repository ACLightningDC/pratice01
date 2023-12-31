package vo;//DTO

import util.SHA256;

public class MemberBean {
	private String id;
	private String grade;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String temporary_password;
	
	//'회원가입 폼'에 없음
	private String joindate;
	
	//반드시 매개변수가 없는 생성자 : 암호화방법-1
	//방법-1 : password 기본값으로 채움 -> 암호화X -> DAO에서 insert 전에 암호화
	//방법-2 : password 기본값으로 채움 -> 암호화O DAO에서 insert
	public MemberBean() {}
	
	//매개변수가 있는 생성자 : 암호화방법-2
	public MemberBean(String id, String grade, String password, String name, String email, String phone) {
		super();
		this.id = id;
		this.grade = grade;
		this.password = SHA256.encodeSHA256(password);
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//password를 암호화 하여 저장
	public void setPasswordSHA256(String password) {
		this.password = SHA256.encodeSHA256(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public String getTemporary_password() {
		return temporary_password;
	}

	public void setTemporary_password(String temporary_password) {
		this.temporary_password = temporary_password;
	}
	
	
	
	//반드시 매개 변수가 없는 생성자
}
