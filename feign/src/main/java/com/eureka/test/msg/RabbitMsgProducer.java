package com.eureka.test.msg;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.eureka.test.config.RabbitMqConfig;

@Component
public class RabbitMsgProducer implements  RabbitTemplate.ConfirmCallback{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendRabbitMsg(String topic,String msg){
		rabbitTemplate.setConfirmCallback(this);
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		
		rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, topic ,msg, correlationId);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println(JSON.toJSONString(correlationData));
		System.out.println(ack+",cause="+cause);
	}
	

}
