package com.amg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Amg
 * @date 2021/11/17 10:12
 */
@SpringBootApplication
@MapperScan(basePackages = "com.amg.dao")
public class QuickInsertMain {
	
	public static void main(String[] args) {
		SpringApplication.run(QuickInsertMain.class, args);
	}
}
