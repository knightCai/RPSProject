package com.service.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Oddnumber {
	private static final long serialVersionUID = 1L;
	private String pkid;
	private String startnum;
	private String endnum;
	private int suplusnum;
	private int amountnum;
	private String usenum;
	private String state;
	private int warnnum;
	private Date createtime;
	private String userid;
	
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public String getStartnum() {
		return startnum;
	}
	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}
	public String getEndnum() {
		return endnum;
	}
	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}
	public int getSuplusnum() {
		return suplusnum;
	}
	public void setSuplusnum(int suplusnum) {
		this.suplusnum = suplusnum;
	}
	public int getAmountnum() {
		return amountnum;
	}
	public void setAmountnum(int amountnum) {
		this.amountnum = amountnum;
	}
	public String getUsenum() {
		return usenum;
	}
	public void setUsenum(String usenum) {
		this.usenum = usenum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getWarnnum() {
		return warnnum;
	}
	public void setWarnnum(int warnnum) {
		this.warnnum = warnnum;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Oddnumber [pkid=" + pkid + ", startnum=" + startnum + ", endnum=" + endnum + ", suplusnum=" + suplusnum
				+ ", amountnum=" + amountnum + ", usenum=" + usenum + ", state=" + state + ", warnnum=" + warnnum
				+ ", createtime=" + createtime + ", userid=" + userid + "]";
	}   
	
	
}
