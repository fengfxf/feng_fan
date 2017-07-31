package com.websocket.test.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.alibaba.fastjson.JSON;

public class MyWebSocketHandler extends  AbstractWebSocketHandler{

	private final static ConcurrentMap<String,WebSocketSession> users = new ConcurrentHashMap<String,WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.putIfAbsent(session.getId(), session);
		System.out.println("session Id:"+session.getId());
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		for(String key:users.keySet()){
			if(key.equals(session.getId())){
				continue;
			}
			users.get(key).sendMessage(message);
		}
		System.out.println(session.getId()+",message:"+JSON.toJSONString(message));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		
		System.out.println(session.getId()+",exception:"+JSON.toJSONString(exception));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("before remove="+JSON.toJSONString(users));
		users.remove(session.getId());
		System.out.println("after remove="+JSON.toJSONString(users));
		
		System.out.println(session.getId()+",closeStatus:"+JSON.toJSONString(closeStatus));
	}

	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}

}
