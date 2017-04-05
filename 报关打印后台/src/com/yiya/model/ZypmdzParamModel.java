/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ZypmdzParam
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.model;

/**
 * ZypmdzParam.
 * @author knight
 */
public class ZypmdzParamModel extends BaseModel {

	private Integer id;//   主键	private String chainname;//   中文品名	private String englishname;//   英文品名	public Integer getId() {	    return this.id;	}	public void setId(Integer id) {	    this.id=id;	}	public String getChainname() {	    return this.chainname;	}	public void setChainname(String chainname) {	    this.chainname=chainname;	}	public String getEnglishname() {	    return this.englishname;	}	public void setEnglishname(String englishname) {	    this.englishname=englishname;	}

}