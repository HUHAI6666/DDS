package cn.java.entity;
/**
 * 12306账号实体类
 * @author
 *
 */
public class Account {
	//用户名
	private String userName;
	//密码
	private String password;
	//姓名
	private String name;
	//身份证号
	private String idNo;
	//昵称
	private String nickName;
	//手机号
	private String phone;
	//邮箱
	private String email;
	
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password="
				+ password + ", name=" + name + ", idNo=" + idNo
				+ ", nickName=" + nickName + ", phone=" + phone + ", email="
				+ email + "]";
	}
	
	
}
