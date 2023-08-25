package vo;

import util.SHA256;

public class MemberPwChange {
	private String id ;
	private String pre_password;
	private String new_password;
	
	public MemberPwChange(){}
	
	
	//암호화 생성자
	public MemberPwChange(String id, String pre_password, String new_password) {
		super();
		this.id = id;
		this.pre_password = SHA256.encodeSHA256(pre_password);
		this.new_password = SHA256.encodeSHA256(new_password);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPre_password() {
		return pre_password;
	}

	public void setPre_password(String pre_password) {
		this.pre_password = pre_password;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	
	
	
	
}
