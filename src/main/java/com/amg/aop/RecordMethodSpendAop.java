package com.amg.aop;

import com.amg.anno.RecordMethodSpendAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 * @author Amg
 * @date 2021/11/17 10:36
 */
@Aspect
@Slf4j
@Component
public class RecordMethodSpendAop {
	
	@Pointcut(value = "@annotation(com.amg.anno.RecordMethodSpendAnnotation)")
	public void pointcut() {
	
	}
	
	@Around(value = "pointcut()")
	public Object handlerSpeedOperation(ProceedingJoinPoint joinPoint) throws Throwable {
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		//遇到直接记录，否则跳过
		if (method.isAnnotationPresent(RecordMethodSpendAnnotation.class)) {
			String methodName = method.getName();
			long startTime = System.currentTimeMillis();
			log.info("开始执行方法：{},时间戳：{}",methodName,startTime);
			Object proceed = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			log.info("执行方法结束！时间戳：{}，总共耗时：{}s",endTime ,(endTime-startTime) / 1000);
			return proceed;
		}
		
		return joinPoint.proceed();
	}
}
