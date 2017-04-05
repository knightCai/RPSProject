/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TicketInfo
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.bean;

/**
 * TicketInfo.
 * @author knight
 */
public class TicketInfo extends BaseBean {

	private Integer id;//   主键	private String sernum;//   序号	private String bino;//   提单号	private String delnum;//   订单号	private String wpname;//   物品名称	private String brand;//   品牌	private String spec;//   规格	private String count;//   数量	private String price;//   购买单价	private String currency;//   币种	private String discount;//   折扣金额	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getSernum() {	    return this.sernum;	}	public void setSernum(String sernum) {	    this.sernum=sernum;	}	public String getBino() {	    return this.bino;	}	public void setBino(String bino) {	    this.bino=bino;	}	public String getDelnum() {	    return this.delnum;	}	public void setDelnum(String delnum) {	    this.delnum=delnum;	}	public String getWpname() {	    return this.wpname;	}	public void setWpname(String wpname) {	    this.wpname=wpname;	}	public String getBrand() {	    return this.brand;	}	public void setBrand(String brand) {	    this.brand=brand;	}	public String getSpec() {	    return this.spec;	}	public void setSpec(String spec) {	    this.spec=spec;	}	public String getCount() {	    return this.count;	}	public void setCount(String count) {	    this.count=count;	}	public String getPrice() {	    return this.price;	}	public void setPrice(String price) {	    this.price=price;	}	public String getCurrency() {	    return this.currency;	}	public void setCurrency(String currency) {	    this.currency=currency;	}	public String getDiscount() {	    return this.discount;	}	public void setDiscount(String discount) {	    this.discount=discount;	}

}