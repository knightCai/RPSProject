/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： XysbhParam
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.bean;

/**
 * XysbhParam.
 * @author knight
 */
public class XysbhParam extends BaseBean {

	private Integer id;//   主键	private String paramname;//   参数名	private String paramnum;//   税号	private String unit;//   单位	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getParamname() {	    return this.paramname;	}	public void setParamname(String paramname) {	    this.paramname=paramname;	}	public String getParamnum() {	    return this.paramnum;	}	public void setParamnum(String paramnum) {	    this.paramnum=paramnum;	}	public String getUnit() {	    return this.unit;	}	public void setUnit(String unit) {	    this.unit=unit;	}

}