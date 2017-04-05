package com.yiya.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiya.mapper.KhUserMapper;
import com.yiya.model.BaseModel;
import com.yiya.model.KhUserModel;

/**
 * 
 * <br>
 * <b>功能：</b>SysUserService<br>
 * <b>作者：</b>knight<br>
 * <b>日期：</b> Dec 9, 2011 <br>
 * <b>版权所有：
 */
@Service("khUserService")
public class KhUserService<T> extends BaseService<T> {
	private final static Logger log= Logger.getLogger(KhUserService.class);

	@Autowired
    private KhUserMapper<T> mapper;

		
	public KhUserMapper<T> getMapper() {
		return mapper;
	}

	public List<T> getUserCountByUserid(String userid) {
		KhUserModel bm = new KhUserModel();
		bm.setUserid(userid);
		return mapper.queryByList(bm);
	}
	
	public List<T> queryByList() throws Exception{
		KhUserModel model = new KhUserModel();
		model.getPager().setPageSize(10000);
		return getMapper().queryByList(model);
	}
}
