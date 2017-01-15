package com.service.model;

import java.io.Serializable;

public class Userinfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pkid;
	private String userid;
	private String username;
	private String password;
	private String state;

	
	public String getPkid() {
		return pkid;
	}


	public void setPkid(String pkid) {
		this.pkid = pkid;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Userinfo [pkid=" + pkid + ", userid=" + userid + ", username=" + username + ", password=" + password
				+ ", state=" + state + "]";
	}

}
