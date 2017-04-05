package com.yiya.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.mapper.OddNumMapper;

/**
 * 
 * <br>
 * <b>功能：</b>SysUserService<br>
 * <b>作者：</b>knight<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：
 */
@Service("OddNumService")
public class OddNumService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(OddNumService.class);

	@Autowired
    private OddNumMapper<T> mapper;

		
	public OddNumMapper<T> getMapper() {
		return mapper;
	}


	public int getUserCountById(String id) {
		return mapper.queryByCountById(id);
	}
}
