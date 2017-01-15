package com.service.model;

import java.util.Date;

public class Logisticslisting {
	private static final long serialVersionUID = 1L;
	
	 /* 
logistic.setPkid
logistic.setDeclarenum
logistic.setSerialnum
logistic.setExpressnum
logistic.setCargoname
logistic.setCargotype
logistic.setCount
logistic.setBrand
logistic.setDeclareweight
logistic.setDeclareprice
logistic.setDeclarepricesum
logistic.setLegalnum
logistic.setLegalunit
logistic.setNetweight
logistic.setPackagecount
logistic.setCargoid
logistic.setConsigneename
logistic.setConsigneeaddr
logistic.setConsigneephone
logistic.setConsignercardid
logistic.setConsignername
logistic.setConsigneraddr
logistic.setConsignerphone
logistic.setConsignercountry
logistic.setConsigneecountry
logistic.setIsprint
logistic.setImportnum
logistic.setCreatetime
logistic.setImportuser
	   	*/
	private String pkid;
	private String declarenum;
	private String serialnum;
	private String expressnum;
	private String cargoname;
	private String cargotype;
	private String brand;
	private String declareweight;
	private String declareprice;
	private String declarepricesum;
	private String legalnum;
	private String legalunit;
	private String netweight;
	private String cargoid;
	private String packagecount;
	private String count;
	private String consigneename;
	private String consigneeaddr;
	private String consigneephone;
	private String consignercardid;
	private String consignername;
	private String consigneraddr;
	private String consignerphone;
	private String consignercountry;
	private String consigneecountry;
	private String isprint;
	private String importnum;
	private Date createtime;
	private String importuser;
	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public String getDeclarenum() {
		return declarenum;
	}
	public void setDeclarenum(String declarenum) {
		this.declarenum = declarenum;
	}
	public String getSerialnum() {
		return serialnum;
	}
	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
	public String getExpressnum() {
		return expressnum;
	}
	public void setExpressnum(String expressnum) {
		this.expressnum = expressnum;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getCargotype() {
		return cargotype;
	}
	public void setCargotype(String cargotype) {
		this.cargotype = cargotype;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDeclareweight() {
		return declareweight;
	}
	public void setDeclareweight(String declareweight) {
		this.declareweight = declareweight;
	}
	public String getDeclareprice() {
		return declareprice;
	}
	public void setDeclareprice(String declareprice) {
		this.declareprice = declareprice;
	}
	public String getDeclarepricesum() {
		return declarepricesum;
	}
	public void setDeclarepricesum(String declarepricesum) {
		this.declarepricesum = declarepricesum;
	}
	public String getLegalnum() {
		return legalnum;
	}
	public void setLegalnum(String legalnum) {
		this.legalnum = legalnum;
	}
	public String getLegalunit() {
		return legalunit;
	}
	public void setLegalunit(String legalunit) {
		this.legalunit = legalunit;
	}
	public String getNetweight() {
		return netweight;
	}
	public void setNetweight(String netweight) {
		this.netweight = netweight;
	}
	public String getPackagecount() {
		return packagecount;
	}
	public void setPackagecount(String packagecount) {
		this.packagecount = packagecount;
	}
	public String getCargoid() {
		return cargoid;
	}
	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}
	public String getConsigneename() {
		return consigneename;
	}
	public void setConsigneename(String consigneename) {
		this.consigneename = consigneename;
	}
	public String getConsigneeaddr() {
		return consigneeaddr;
	}
	public void setConsigneeaddr(String consigneeaddr) {
		this.consigneeaddr = consigneeaddr;
	}
	public String getConsigneephone() {
		return consigneephone;
	}
	public void setConsigneephone(String consigneephone) {
		this.consigneephone = consigneephone;
	}
	public String getConsignercardid() {
		return consignercardid;
	}
	public void setConsignercardid(String consignercardid) {
		this.consignercardid = consignercardid;
	}
	public String getConsignername() {
		return consignername;
	}
	public void setConsignername(String consignername) {
		this.consignername = consignername;
	}
	public String getConsigneraddr() {
		return consigneraddr;
	}
	public void setConsigneraddr(String consigneraddr) {
		this.consigneraddr = consigneraddr;
	}
	public String getConsignerphone() {
		return consignerphone;
	}
	public void setConsignerphone(String consignerphone) {
		this.consignerphone = consignerphone;
	}
	public String getConsignercountry() {
		return consignercountry;
	}
	public void setConsignercountry(String consignercountry) {
		this.consignercountry = consignercountry;
	}
	public String getConsigneecountry() {
		return consigneecountry;
	}
	public void setConsigneecountry(String consigneecountry) {
		this.consigneecountry = consigneecountry;
	}
	public String getIsprint() {
		return isprint;
	}
	public void setIsprint(String isprint) {
		this.isprint = isprint;
	}
	public String getImportnum() {
		return importnum;
	}
	public void setImportnum(String importnum) {
		this.importnum = importnum;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getImportuser() {
		return importuser;
	}
	public void setImportuser(String importuser) {
		this.importuser = importuser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Logisticslisting [pkid=" + pkid + ", declarenum=" + declarenum + ", serialnum=" + serialnum
				+ ", expressnum=" + expressnum + ", cargoname=" + cargoname + ", cargotype=" + cargotype + ", count="
				+ count + ", brand=" + brand + ", declareweight=" + declareweight + ", declareprice=" + declareprice
				+ ", declarepricesum=" + declarepricesum + ", legalnum=" + legalnum + ", legalunit=" + legalunit
				+ ", netweight=" + netweight + ", packagecount=" + packagecount + ", cargoid=" + cargoid
				+ ", consigneename=" + consigneename + ", consigneeaddr=" + consigneeaddr + ", consigneephone="
				+ consigneephone + ", consignercardid=" + consignercardid + ", consignername=" + consignername
				+ ", consigneraddr=" + consigneraddr + ", consignerphone=" + consignerphone + ", consignercountry="
				+ consignercountry + ", consigneecountry=" + consigneecountry + ", isprint=" + isprint + ", importnum="
				+ importnum + ", createtime=" + createtime + ", importuser=" + importuser + "]";
	}
	
	
}
