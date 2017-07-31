package com.test.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
/**
 * 发送消息步骤：
 * a、启动rabbitMq。
 * b、消费者先绑定channel申请的队列名，对应的exchange、route-key上，然后等待消费。
 * c、生产者申请消费者绑定的exchange，并定义exchange-type。
 * d、生产者在消费者关注的exchange、route-key上发布消息。
 * 备注：
 * 1、""是默认的exchange 不允许在默认的exchange上绑定exhange type。
 * 2、生产者和消费者必须使用相同的route-key、exchange。
 * 3、消费者绑定的queue是系统分配的可以用channel.queueDeclare().getQueue()获得。
 * 4、MessageProperties.PERSISTENT_TEXT_PLAIN标记消息持久化。
 * 5、topic方式支持模糊匹配，*匹配一组，#匹配多组
 * @author geping
 */
public class Producer_test {
	
	
	public static void main(String[] args){
		try {
			ConnectionFactory cf = new ConnectionFactory();
			cf.setHost("localhost");
			cf.setUsername("user_admin");
			cf.setPassword("admin123456");
			Connection cc = cf.newConnection();
			Channel cl = cc.createChannel();
			//cl.exchangeDeclare("topic_test", "topic");
			cl.exchangeDeclare("topic_test","topic");
			//"" 默认就是按队列direct delivery
			cl.basicPublish("topic_test", "test", MessageProperties.PERSISTENT_TEXT_PLAIN, "1111hello rabbitMq".getBytes());
			
			cl.close();
			cc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
