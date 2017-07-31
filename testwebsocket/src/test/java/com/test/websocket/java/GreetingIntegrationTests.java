package com.test.websocket.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.websocket.test.config.MyWebSocketHandler;

@RunWith(SpringRunner.class)
public class GreetingIntegrationTests {


    @Test
    public void getGreeting() throws Exception {
    	
    	
    	List<Transport> transports = new ArrayList<>();
        WebSocketTransport wst = new WebSocketTransport(new StandardWebSocketClient());
        
        //wst.connect(request, handler)
        transports.add(wst);
        
        
        SockJsClient sockJsClient = new SockJsClient(transports);
        ListenableFuture<WebSocketSession> session = sockJsClient.doHandshake(new MyWebSocketHandler(), "ws://192.168.0.147:8088/ws");
        
        
        session.get().sendMessage(new WebSocketMessage(){

			@Override
			public Object getPayload() {
				
				return "hello message";
			}

			@Override
			public int getPayloadLength() {
				return "hello message".length();
			}

			@Override
			public boolean isLast() {
				return false;
			}
        	
        });

    }

}
