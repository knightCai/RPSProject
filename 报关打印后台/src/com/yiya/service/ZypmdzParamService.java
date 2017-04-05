/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ZypmdzParam
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.bean.ZypmdzParam;
import com.yiya.mapper.ZypmdzParamMapper;
import com.yiya.model.ZypmdzParamModel;

/**
 * ZypmdzParam.
 * @author knight
 */
@Service("zypmdzParamService")
public class ZypmdzParamService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);

	@Autowired
    private ZypmdzParamMapper<T> mapper;

		
	public ZypmdzParamMapper<T> getMapper() {
		return mapper;
	}

	public void insertbatch(List<ZypmdzParam> zypmdzlist) {
		mapper.insertbatch(zypmdzlist);
	}
	
	public void deleteDouble() {
		mapper.deleteDouble();
	}

	public List<ZypmdzParam> queryListByParam(ZypmdzParamModel model) {
		return mapper.queryListByParam(model);
	}
}