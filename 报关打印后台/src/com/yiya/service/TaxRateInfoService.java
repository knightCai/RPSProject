/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TaxRateInfo
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.bean.TaxRateInfo;
import com.yiya.mapper.TaxRateInfoMapper;

/**
 * TaxRateInfo.
 * @author knight
 */
@Service("taxRateInfoService")
public class TaxRateInfoService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);

	@Autowired
    private TaxRateInfoMapper<T> mapper;

		
	public TaxRateInfoMapper<T> getMapper() {
		return mapper;
	}
	
	public void insertbatch(List<TaxRateInfo> taxRateInfolist) {
		mapper.insertbatch(taxRateInfolist);
	}

	public void deleteDouble() {
		mapper.deleteDouble();
	}
}