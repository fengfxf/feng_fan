package com.eureka.test.ctrl;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.test.config.RabbitMqConfig;
import com.eureka.test.msg.RabbitMsgProducer;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/balancer")
public class TestBalancerCtrl {
	@Value("${client.one.path}")
	private String clientOnePath;
	@Value("${client.one.name}")
	private String clientOneName;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	RabbitMsgProducer rabbitMsgProducer;
	
	@GetMapping("/helloB/{name}")
	public String testHello(@PathVariable("name") String name){
		
		return this.restTemplate.getForObject(clientOnePath+name, String.class);
	}
	
	@GetMapping("/hello_test")
	public String test_Hello(){
		URI uri = this.loadBalancerClient.choose(clientOneName).getUri();
		return uri.getPath()+">>"+uri.toString();
	}
	
	@GetMapping("/home_url")
	public String test_home_url(){
		return  eurekaClient.getNextServerFromEureka(clientOneName, false).getHomePageUrl();
	}
	
	@GetMapping("/rabbit/send/{msg}")
	public String testRabbitSendMsg(@PathVariable("msg") String msg){
		
		rabbitMsgProducer.sendRabbitMsg(RabbitMqConfig.TOPIC_NAME,msg);
		
		return  "1";
	}
}
