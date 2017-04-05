/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： LogisTics
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.mapper;

import java.util.List;

import com.yiya.bean.LogisTics;
import com.yiya.model.LogisTicsModel;

/**
 * LogisTics.
 * @author knight
 */
public interface LogisTicsMapper<T> extends BaseMapper<T> {

	List<T> queryListByParam(LogisTicsModel model);

	void updatebatchforBGStatu(List<LogisTics> logislist);

}