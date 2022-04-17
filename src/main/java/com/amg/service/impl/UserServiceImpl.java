package com.amg.service.impl;

import com.amg.anno.RecordMethodSpendAnnotation;
import com.amg.dao.UserMapper;
import com.amg.domain.User;
import com.amg.service.UserService;
import com.amg.utils.Commutil;
import com.amg.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 *
 * @author Amg
 * @date 2021/11/17 10:27
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	
	@Resource
	private UserMapper userMapper;
	
	private ThreadPoolExecutor threadPool = ThreadUtil.getThreadPoolExecutorDefault(5 ,10);
	
	
	@Override
	@RecordMethodSpendAnnotation
	public List<User> queryAllUseSync() {
		
		return userMapper.queryAll();
	}
	
	@Override
	@RecordMethodSpendAnnotation
	public List<User> queryAllUseThreadPool(int limit) {
		
		
		Long count = userMapper.getCount();
		
		List<FutureTask<List<User>>> resultList = new ArrayList<>();
		
		
		long cycles = count / limit;
		for (int i = 0; i < cycles; i++) {
			
			long idx = i * limit;
			log.info("idx: {}" ,idx);
			FutureTask<List<User>> futureTask = new FutureTask<>(() -> userMapper.queryAllByLimit(idx ,limit));
			
			threadPool.execute(futureTask);
			resultList.add(futureTask);
		}
		List<User> result = new ArrayList<>();
		Iterator<FutureTask<List<User>>> iterator = resultList.iterator();
		while (iterator.hasNext()) {
			try {
				result.addAll(iterator.next().get());
				iterator.remove();
			} catch (InterruptedException | ExecutionException e) {
				log.error("多线程查询出现异常：{}" ,e.getMessage());
			}
		}
		
		//最后一次数据可能不为整，需要额外操作
		if (result.size() != count)
			result.addAll(userMapper.queryAllByLimit(result.size() ,Math.toIntExact(count)));
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	@RecordMethodSpendAnnotation
	public int insertBatch(int num) {
		
		int cnt = 0;
		
		List<User> list = new ArrayList<>();
		//构造完数据
		for (int i = 0; i < num; i++) {
			list.add(new User(Commutil.getRandomStr(10) ,new Date()));
		}
		
		//分批导入,每次导入10W条
		int cycles = num / 100000 + 1;
		for (int i = 0; i < cycles; i++) {
			int fromIdx = i * 100000;
			int toIdx = (i + 1) * 100000;
			if (toIdx > list.size())
				toIdx = list.size();
			List<User> subList = list.subList(fromIdx ,toIdx);
			int successCnt = userMapper.insertBatch(subList);
			cnt += successCnt;
			log.info("批量插入成功！本次插入条数:{},总记录数：{}" ,successCnt ,cnt);
		}
		
		
		return cnt;
	}
	
	
	@Override
	@RecordMethodSpendAnnotation
	public List<User> queryAllUseSyncAndLimit(int limit) {
		
		List<User> list = new ArrayList<>();
		Long count = userMapper.getCount();
		//循环次数
		long cycles = count / limit;
		List<User> tmp;
		for (int i = 0; i < cycles; i++) {
			
			long startIdx = i * limit;
			long endIdx = (i + 1) * limit;
			log.info("startIdx:{},endIdx:{}" ,startIdx ,endIdx);
			if (endIdx > count)
				endIdx = count;
			tmp = userMapper.queryAllByLimit(startIdx ,Math.toIntExact(endIdx));
			list.addAll(tmp);
			tmp = null;
			System.gc();
		}
		
		return list;
	}
	
}
