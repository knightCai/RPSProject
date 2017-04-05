/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TaxRateInfo
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.mapper;

import java.util.List;
/**
 * TaxRateInfo.
 * @author knight
 */
public interface TaxRateInfoMapper<T> extends BaseMapper<T> {
	
	void insertbatch(List list);
	
	void deleteDouble();

}