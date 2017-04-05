/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： XysbhParam
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.bean.XysbhParam;
import com.yiya.mapper.XysbhParamMapper;

/**
 * XysbhParam.
 * @author knight
 */
@Service("xysbhParamService")
public class XysbhParamService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);

	@Autowired
    private XysbhParamMapper<T> mapper;

		
	public XysbhParamMapper<T> getMapper() {
		return mapper;
	}
	
	public XysbhParam queryByName(String paramname){
		return mapper.queryByName(paramname);
	}

	public void insertbatch(List<XysbhParam> xysbhlist) {
		mapper.insertbatch(xysbhlist);
	}

	public void deleteDouble() {
		mapper.deleteDouble();
	}

}