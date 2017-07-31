package com.zuul.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 *  此应用主要测试zuul的特性：
 *  1、@EnableZuulProxy是组合注解，已经保护eureka客户端
 *  2、zuul已经已经保护默认轮询的负载均衡
 *  3、可以直接使用ribbon配置文件配置负载均衡的策略,前提路由映射也需要在配置文件中配置
 *  4、zuul支持正则表达式的方式做url正则匹配代理转发，此时配置文件负载均衡策略无效
 *  5、zuul默认每个服务都实现hystrix策略，@EnableHystrixDashboard就可以在web查看健康监控
 *  6、EnableZuulProxy包含EnableZuulServer，已经有过滤器和路由功能。EnableZuulServer是空白zuul
 *  7、建议使用zuul的配置文件处理路由映射，理由，灵活、隐藏为服务名
 * 
 * @author geping
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableZuulProxy
public class ZuulServerApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}

	/**
	 * name 是微服务的service id
	 * @return
	 */
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
	    return new PatternServiceRouteMapper(
	        "(?<name>^.+$)",
	        "${name}/");
	}
	
//	@Bean
//	public PatternServiceRouteMapper serviceRouteMapper() {
//	    return new PatternServiceRouteMapper(
//	        "(?<name>^.+)-(?<version>v.+$)",
//	        "${version}/${name}/");
//	}
}
