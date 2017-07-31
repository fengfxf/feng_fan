package com.websocket.test.ctrl;

import java.util.ServiceLoader;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
	
import com.test.websocket.serviceload.TestClass.Iservice;
/**
 * ServiceLoader 使用步骤
 * 1、在项目src目录下创建META-INF/services目录
 * 2、在services目录里创建文件，文件的名字是接口全路径名（此文件叫映射文件）。
 * 3、把编译好的接口实现类的class文件放到classpath 对应的位置
 * 4、在上述文件里加入接口实现类的全路径名
 * 备注：ServiceLoader接口实现类切换时最好先备份映射文件，当出现问题时即可还原映射文件即可。
 *
 */
@Controller
@RequestMapping("/sv")
public class ServiceLoadTestCtrl {

	@GetMapping("/test")
	public String testServiceLoad(){
		
		ServiceLoader<Iservice> serviceLoader = ServiceLoader.load(Iservice.class);
		String s = "1";
		if(serviceLoader.iterator().hasNext()){
			s = serviceLoader.iterator().next().SayHello();
		}
		return s;
	}
	
	@RequestMapping("/ack")
    public String helloHtml(){
        
        return"/sck";
    }
}
