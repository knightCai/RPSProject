package com.yiya.bean;

public class KhUser extends BaseBean {

	private String id;
	private String userid;
	private String username;
	private String password;
	private String state;
	private int type;	//类型(1-系统 2-自定义)对应不同的单号
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "KhUser [id=" + id + ", userid=" + userid + ", username="
				+ username + ", password=" + password + ", state=" + state
				+ ", type=" + type + "]";
	}

}
