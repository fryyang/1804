package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.jt.feign.EurekaServiceFeign;
import cn.jt.feign.SidecarServiceFeign;


@RestController
public class HelloController {
	@Autowired
	EurekaServiceFeign EurekaServiceImpl;
	@Autowired
	SidecarServiceFeign sidecarServiceFeign;
	@RequestMapping(value="/hello/{name}")
	@ResponseBody
	@HystrixCommand(fallbackMethod="fallbackhello")
	public String hello(@PathVariable("name")String name){
		return EurekaServiceImpl.hello(name);
	}
	public String fallbackhello(String name){
		return "tony";
	}
	//实现sidecar对nodejs封装，返回欢迎页面
	@RequestMapping("/node")
	public String node(){
		return sidecarServiceFeign.node();//在feign接口中定义
	}
}
