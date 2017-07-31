package com.test.websocket.serviceload.TestClass;

public class TestOneIservice implements Iservice {

	@Override
	public String SayHello() {
		
		return "hell one";
	}

	@Override
	public String getName() {
		
		return "Test One Iservice";
	}

}
