package com.test.websocket.serviceload.TestClass;

public class TestTwoIservice implements Iservice {

	@Override
	public String SayHello() {
		
		return "Hell two";
	}

	@Override
	public String getName() {
		
		return "Test Two Iservice";
	}

}
