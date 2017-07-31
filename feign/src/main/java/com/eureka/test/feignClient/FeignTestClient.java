package com.eureka.test.feignClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eurekaclienthello")
public interface FeignTestClient {

	@RequestMapping(value = "/eureka_client/{name}", method = RequestMethod.GET)
	public String feignHello(@PathVariable("name") String name);
	
	@RequestMapping(value = "/hystrix_test/{name}", method = RequestMethod.GET)
	public String hystrixHello(@PathVariable("name") String name);
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String fetchUserById(@PathVariable("id") Integer id);
}
