package com.test.websocket.serviceload;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

import com.alibaba.fastjson.JSON;
import com.test.websocket.serviceload.TestClass.Iservice;

public class ServiceLoadTest {

	public static void main(String[] args) {
		String classPath = "/Users/geping/Downloads/distribution/code/test-spring-cloud-fan/testwebsocket/target/classes/com/test/websocket/serviceload/TestClass/";
		String javaPath = "/Users/geping/Downloads/distribution/code/test-spring-cloud-fan/testwebsocket/src/main/java/com/test/websocket/serviceload/TestClass";
		//File clazzPath = new File(javaPath+"TestOneIservice");
		File clazzPath = new File(javaPath);
		String className = "com.test.websocket.serviceload.TestClass.TestOneIservice";
		
		try {
//			URLClassLoader loader = new URLClassLoader(new URL[]{ clazzPath.toURI().toURL()});
//			Class aClass = loader.loadClass(className);
//			Iservice service = (Iservice)aClass.newInstance();
			Iservice service = (Iservice) Class.forName(className).newInstance();
			
			System.out.println(service.getName());  
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
//		ServiceLoader<Iservice> serviceLoader = ServiceLoader.load(Iservice.class);  
//		System.out.println(serviceLoader.iterator().hasNext());
//		for(Iservice service : serviceLoader) {  
//            System.out.println(service.SayHello());  
//        }  
	}

}
