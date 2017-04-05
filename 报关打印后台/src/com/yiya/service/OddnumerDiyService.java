/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： OddnumerDiy
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.bean.OddnumerDiy;
import com.yiya.mapper.OddnumerDiyMapper;

/**
 * OddnumerDiy.
 * @author knight
 */
@Service("oddnumerDiyService")
public class OddnumerDiyService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);

	@Autowired
    private OddnumerDiyMapper<T> mapper;

		
	public OddnumerDiyMapper<T> getMapper() {
		return mapper;
	}


	public void insertbatch(List<OddnumerDiy> numdiylist) {
		mapper.insertbatch(numdiylist);
	}

}