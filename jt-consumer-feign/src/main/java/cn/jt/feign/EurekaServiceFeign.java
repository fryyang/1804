package cn.jt.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//一个feign客户端程序，去调用服务提供者，通过applocation,name
@FeignClient(value="provider-user")
public interface EurekaServiceFeign {
	//仿照服务提供者写一个接口方法
	@RequestMapping(value="/hello/{name}",method=RequestMethod.GET)
	public String hello(@PathVariable("name") String name);
}
