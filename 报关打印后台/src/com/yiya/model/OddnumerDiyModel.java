/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： OddnumerDiy
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.model;

/**
 * OddnumerDiy.
 * @author knight
 */
public class OddnumerDiyModel extends BaseModel {

	private Integer id;//   主键	private String num;//   报关单号	private Integer type;//   单号类型(1-国内 2-陕西省)	private Integer status;//   状态(0-未使用 1-已使用)	private String useuserid;//   使用用户	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getNum() {	    return this.num;	}	public void setNum(String num) {	    this.num=num;	}	public Integer getType() {	    return this.type;	}	public void setType(Integer type) {	    this.type=type;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public String getUseuserid() {	    return this.useuserid;	}	public void setUseuserid(String useuserid) {	    this.useuserid=useuserid;	}

}