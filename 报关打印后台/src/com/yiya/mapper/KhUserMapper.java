package com.yiya.mapper;

import com.yiya.model.SysUserModel;

/**
 * SysUser Mapper
 * @author Administrator
 *
 */
public interface KhUserMapper<T> extends BaseMapper<T> {

	public int getUserCountByUserid(String userid);
	
}
