package cn.java.entity;

public class Rule {
	
	private int id;
	private String name ;
	private String idNo ;
	private String phone ;
	private String address ;
	private String email ;
	private String cardNo ;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Rule() {
		super();
	}
	@Override
	public String toString() {
		return "Rule [id=" + id + ", name=" + name + ", idNo=" + idNo
				+ ", phone=" + phone + ", address=" + address + ", email="
				+ email + ", cardNo=" + cardNo + "]";
	}
	
}
