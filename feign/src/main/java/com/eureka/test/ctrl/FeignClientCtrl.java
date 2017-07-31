package com.eureka.test.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.eureka.test.feignClient.FeignTestClient;
import com.eureka.test.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/myServer")
public class FeignClientCtrl {
	
	@Autowired
	private FeignTestClient feignTestClient;
	
	@GetMapping("/fetchUser/{id}")
	public User fetchUserById(@PathVariable("id") Integer id){
		
		return JSON.parseObject(feignTestClient.fetchUserById(id), User.class);
	}
	
	@GetMapping("/hello/{name}")
	@HystrixCommand(fallbackMethod="HystrixCommandMethod")
	public String testHello(@PathVariable("name") String name){
		testExceptionMethod(name);
		return feignTestClient.feignHello(name);
	}
	/**
	 * maxAttempts 最大尝试次数，超过此值断路器开启，将直接调用fallback方法；
	 * openTimeout 断路器开启后持续时间，在此时间内的请求将直接调用fallback方法，超过此事件将重新调用此方法；
	 * resetTimeout 本意是：断路器打开后这3s后，再调用此方法一次看看是否回复正常，
	 * 				如果正常后面将调用此方法，如果还是失败后面请求将直接进入fallback方法。
	 * 				但是：此次测试并未达到本意的描述。
	 */
	@GetMapping("/hystrix/{name}")
//	@HystrixCommand(fallbackMethod="HystrixCommandMethod")
//	@Retryable(include=Exception.class)
	@CircuitBreaker(include = Exception.class,maxAttempts=5,openTimeout = 10000L,resetTimeout = 3000L)
	public String hystrixHello(@PathVariable("name") String name){
		testExceptionMethod(name);
		return feignTestClient.hystrixHello(name);
	}
	private void testExceptionMethod(String name){
		if(name.matches("^\\d{1,}$")){
			System.out.println("retry test>>"+name);
			throw new RuntimeException();
		}
	}
	
	@Recover
	private String HystrixCommandMethod(String name){
		System.out.println("fallback test>>"+name);
		return "hello "+name+" error";
	}
}
