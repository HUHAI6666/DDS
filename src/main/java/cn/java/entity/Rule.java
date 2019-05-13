package cn.java.entity;

public class Rule {
	
	private int id;
	private String name ;
	private String idNo ;
	private String phone ;
	private String nickName ;
	private String email ;
	private String userName ;
	private String password ;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Rule() {
		super();
	}
	@Override
	public String toString() {
		return "Rule [id=" + id + ", name=" + name + ", idNo=" + idNo
				+ ", phone=" + phone + ", nickName=" + nickName + ", email="
				+ email + ", userName=" + userName + ", password=" + password
				+ "]";
	}
	
}
