package com.test.rabbit;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.eureka.test.util.RabbitConsumer;

public class Cosumer_test {
	
	//private static String queueName = "fanqueue";
	
	public static void main(String[] args){
		try {
			ConnectionFactory cf = new ConnectionFactory();
			
			cf.setHost("localhost");
			cf.setUsername("user_admin");
			cf.setPassword("admin123456");
			Connection cc = cf.newConnection();
			Channel cl = cc.createChannel();
			//cl.exchangeDeclare("ex_test", "direct");
			cl.exchangeDeclare("topic_test", "topic");
			String qname = cl.queueDeclare().getQueue();
			
			System.out.println(qname);
			cl.queueBind(qname, "topic_test", "test");
			RabbitConsumer rc = new RabbitConsumer(cl);
			cl.basicConsume(qname, false,rc);
			
			//cl.queueBind(queue, exchange, routingKey)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
