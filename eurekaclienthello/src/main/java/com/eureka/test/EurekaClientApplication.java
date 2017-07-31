package com.eureka.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.eureka.test.model.User;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/eureka_client/{name}")
    public String helloEureka(@PathVariable("name") String name) {
		String s = port+"=Hello eureka :"+name;
		System.out.println(s);
        return s;
    }
	
	@RequestMapping("/hystrix_client/{name}")
    public String HelloHystrix(@PathVariable("name") String name) {
		String s = port+"=Hello hystrix :"+name;
		System.out.println(s);
        return s;
    }
	
	@RequestMapping("/user/{id}")
    public String fetchUserById(@PathVariable("id") Integer id) {
		System.out.println("用户ID是："+id);
		User u = new User();
		u.setId(id);
		u.setAddr("eureka addr client");
		u.setAge(8);
		u.setHt(3.4f);
		u.setWg(55.1f);
		return JSON.toJSONString(u);
    }

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

}
