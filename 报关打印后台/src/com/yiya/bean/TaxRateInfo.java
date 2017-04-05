/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TaxRateInfo
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.bean;

/**
 * TaxRateInfo.
 * @author knight
 */
public class TaxRateInfo extends BaseBean {

	private Integer id;//   主键	private String taxnum;//   税号	private String rate;//   税率	private String mmo1;//   预留字段1	private String mmo2;//   预留字段2	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getTaxnum() {	    return this.taxnum;	}	public void setTaxnum(String taxnum) {	    this.taxnum=taxnum;	}	public String getRate() {	    return this.rate;	}	public void setRate(String rate) {	    this.rate=rate;	}	public String getMmo1() {	    return this.mmo1;	}	public void setMmo1(String mmo1) {	    this.mmo1=mmo1;	}	public String getMmo2() {	    return this.mmo2;	}	public void setMmo2(String mmo2) {	    this.mmo2=mmo2;	}

}