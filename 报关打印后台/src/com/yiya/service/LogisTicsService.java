/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： LogisTics
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.bean.LogisTics;
import com.yiya.mapper.LogisTicsMapper;
import com.yiya.model.LogisTicsModel;

/**
 * LogisTics.
 * @author knight
 */
@Service("logisTicsService")
public class LogisTicsService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);

	@Autowired
    private LogisTicsMapper<T> mapper;

		
	public LogisTicsMapper<T> getMapper() {
		return mapper;
	}
	
	public  List<T> queryListByParam(LogisTicsModel model){
		return mapper.queryListByParam(model);
	}

	public void updatebatchforBGStatu(List<LogisTics> logislist) {
		mapper.updatebatchforBGStatu(logislist);
	}
}