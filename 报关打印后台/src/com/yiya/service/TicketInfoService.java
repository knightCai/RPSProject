/*
 * 文件名：
 * 版权：Copyright 2017-2020 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TicketInfo
 * 修改人：knight
 * 修改时间：${date}
 * 修改内容：新增
 */
package com.yiya.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.bean.TicketInfo;
import com.yiya.mapper.TicketInfoMapper;

/**
 * TicketInfo.
 * @author knight
 */
@Service("ticketInfoService")
public class TicketInfoService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(SiteColumnService.class);

	@Autowired
    private TicketInfoMapper<T> mapper;

		
	public TicketInfoMapper<T> getMapper() {
		return mapper;
	}
	
	public void insertbatch(List<TicketInfo> ticketInfolist) {
		mapper.insertbatch(ticketInfolist);
	}

	public void deleteDouble() {
		mapper.deleteDouble();
	}
}