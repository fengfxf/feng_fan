package com.eureka.test.msg;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;

/**
 * @author geping
 */
@Component
public class RabbitMsgReceiver implements ChannelAwareMessageListener{


	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		System.out.println(JSON.toJSONString(message));
		
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		
		System.out.println("onMessage="+new String(message.getBody()));
		
	}
}
