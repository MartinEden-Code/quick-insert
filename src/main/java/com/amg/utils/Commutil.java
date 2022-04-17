package com.amg.utils;

import java.util.Random;

/**
 * 通用工具类
 * @author Amg
 * @date 2021/11/18 10:03
 */
public class Commutil {
	
	static String source = "abcdefghijklnmop.,/|[]!@#$%^&qrstuvwxyz123456789";
	
	public static String getRandomStr(int len) {
		
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			int idx = random.nextInt(source.length());
			result.append(source.charAt(idx));
		}
		
		return result.toString();
	}
}
