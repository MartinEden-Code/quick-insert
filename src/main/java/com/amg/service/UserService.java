package com.amg.service;


import com.amg.domain.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author Amg
 * @since 2021-11-17 10:23:12
 */
public interface UserService {
	
	/**
	 * 使用寻常的方法查询
	 * @return List<User>
	 */
	List<User> queryAllUseSync();
	
	/**
	 * 使用线程池方式查询，每个线程查询limit条
	 * @param limit 查询条数
	 * @return List<User>
	 */
	List<User> queryAllUseThreadPool(int limit);
	
	/**
	 * 批量导入
	 * @param num 插入数量
	 * @return	 成功导入条数
	 */
	int insertBatch(int num);
	
	/**
	 * 使用寻常的方法查询 + 分页形式
	 * @return List<User>
	 * @param i
	 */
	List<User> queryAllUseSyncAndLimit(int i);
}
