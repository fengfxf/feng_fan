package com.eureka.test.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eureka.test.msg.RabbitMsgReceiver;

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
 * 4、MessageProperties.PERSISTENT_TEXT_PLAIN标记消息持久化。 5、topic方式支持模糊匹配，*匹配一组，#匹配多组
 * 重点：
 * 如果application中有使用SimpleMessageListenerContainer and @RabbitListener 时，程序默认使用SimpleMessageListenerContainer监听器。
 * @author geping
 */
@Configuration
public class RabbitMqConfig {

	public final static String QUEUE_NAME = "spring-topic";
	
	public final static String TOPIC_NAME = "spring.topic";
	
	public final static String EXCHANGE_NAME = "spring-boot-exchange";

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses("127.0.0.1:5672");
		connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername("user_admin");
		connectionFactory.setPassword("admin123456");
		connectionFactory.setPublisherConfirms(true); // 必须要设置
		return connectionFactory;
	}
	
	@Bean  
	public RabbitTemplate rabbitTemplate() {  
	    RabbitTemplate template = new RabbitTemplate(connectionFactory());  
	    return template;  
	} 

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME,true,false);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(TOPIC_NAME);
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(RabbitMsgReceiver receiver) {
		
		return new MessageListenerAdapter(receiver, "onMessage");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		//手动确认
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return container;
	}
}
