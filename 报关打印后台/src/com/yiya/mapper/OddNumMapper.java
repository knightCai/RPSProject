package com.yiya.mapper;

/**
 * SysUser Mapper
 * @author Administrator
 *
 */
public interface OddNumMapper<T> extends BaseMapper<T> {

	int queryByCountById(String id);

}
