package cn.java.entity;

public class Menu {
	
	private int id;
	private String text;
	private String state;
	private String iconCls;
	private String href;
	private int pid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", text=" + text + ", state=" + state
				+ ", iconCls=" + iconCls + ", href=" + href + ", pid=" + pid
				+ "]";
	}
	
	
}
