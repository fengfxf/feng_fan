package com.eureka.test.util;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 仅为test.rabbit 原始api调用测试使用
 * @author geping
 */
public class RabbitConsumer implements Consumer{
	
	private Channel channel;
	
	public RabbitConsumer(Channel channel){
		this.channel = channel;
	}

	@Override
	public void handleConsumeOk(String consumerTag) {
		System.out.println("22222"+consumerTag);
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		System.out.println("1111"+consumerTag);
	}
	@Override
	public void handleCancel(String consumerTag) throws IOException {
		System.out.println("33333"+consumerTag);
	}
	@Override
	public void handleDelivery(String arg0, Envelope arg1, BasicProperties arg2, byte[] arg3) throws IOException {
		System.out.println("66666"+arg0 +",Envelope=="+JSON.toJSONString(arg1));
		
		System.out.println("BasicProperties"+JSON.toJSONString(arg2) +",arg3=="+new String(arg3,0,arg3.length,"utf-8"));
		channel.basicAck(arg1.getDeliveryTag(), false);
	}
	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		System.out.println("444444"+consumerTag);
	}
	@Override
	public void handleRecoverOk(String consumerTag) {
		System.out.println("5555"+consumerTag);
	}


}
