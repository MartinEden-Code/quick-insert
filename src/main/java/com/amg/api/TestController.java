package com.amg.api;

import com.amg.domain.User;
import com.amg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Martin
 * @date 2022/04/18 14:12
 */
@RestController
public class TestController {
	
	@Autowired
	private UserService userService;

	/**
	 * 单线程+基础sql(全表查询)
	 * 缺点：全表查询时间开销很大
	 * @return
	 */
	@GetMapping("/sync")
	public String getData() {
		List<User> list = userService.queryAllUseSync();
		//测试时间10s
		return "查询成功！" + list.size();
	}

	/**
	 * 单线程+分段SQL（走索引）
	 * 缺点：采用分页+循环的方式，会提高效率，但是循环是需要耗费CPU资源的(垃圾收集非常的频繁,等待内存释放，所以CPU也会飙升)，由于请求的对象太大了，
	 * @return
	 */
	@GetMapping("/sync/limit")
	public String getDataByLimit() {
		List<User> list = userService.queryAllUseSyncAndLimit(500000);
		//测试结果2s
		return "查询成功！" + list.size();
	}

	/**
	 * 多线程+分段sql
	 * 分段查询+组合的形式，可以采用多线程异步的形式，每个线程跑完数据拿出来之后就remove掉
	 * @return
	 */
	@GetMapping("/async")
	public String getAsyncData() {
		List<User> list = userService.queryAllUseThreadPool(50000);
		return "查询成功！" + list.size();
	}
	
	@GetMapping("/insertBatch")
	public String insertBatch() {
		
		int cnt = userService.insertBatch(4000000);
		return "插入成功！" + cnt;
	}
}
