package com.eureka.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

/**
 * 此服务测试内容
 * 1、eureka注册与发现
 * 2、restTemplate 分布式请求
 * 3、feign分布式请求 webservice形式,如果是对象参数feign请求，requestMethod必须是post
 * 4、hystrix 断容器 容错机制，hystrixDashboard 请求监控功能,此功能需要在监控的方法上使用HystrixCommand注解
 * 5、ribbon 负载均衡策略目前只考虑使用配置文件，原因是灵活并且可以细化到每个后端配置。
 * 6、Retry 重试机制 Retryable maxAttempts尝试多少次。
 * 7、circuit breaker 断路器,CircuitBreaker
 * @author geping
 *
 */
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableRetry
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
//@RibbonClient(name="eureka-client-hello",configuration=TestConfiguration.class)
public class FeignClientApplication {
	
//	@Bean
//    public IRule ribbonRule() {
//        return new RoundRobinRule();//这里配置策略，和配置文件对应RandomRule,RoundRobinRule,WeightedResponseTimeRule
//    }
	
	@Bean
	@LoadBalanced//启用ribbon的负载均衡默认是轮询
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
		factory.setConnectTimeout(1000);
		factory.setReadTimeout(1000);
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}

}
