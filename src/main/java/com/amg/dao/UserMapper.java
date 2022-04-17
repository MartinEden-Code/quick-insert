package com.amg.dao;

import com.amg.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author Amg
 * @since 2021-11-17 10:23:56
 */
@Mapper
public interface UserMapper {
	
	/**
	 * 查询指定行数据
	 *
	 * @param offset 查询起始位置
	 * @param limit  查询条数
	 * @return 对象列表
	 */
	List<User> queryAllByLimit(@Param("offset") long offset ,@Param("limit") int limit);
	
	
	/**
	 * 通过实体作为筛选条件查询
	 *
	 * @return 对象列表
	 */
	List<User> queryAll();
	
	/**
	 * 获取总记录数
	 * @return	int
	 */
	Long getCount();
	
	/**
	 * 批量导入
	 * @param list 导入list
	 * @return	   导入条数
	 */
	int insertBatch(List<User> list);

	
}
