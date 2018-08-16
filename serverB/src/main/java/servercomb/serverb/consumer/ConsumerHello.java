package servercomb.serverb.consumer;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.springframework.stereotype.Component;

import servicecomb.demo.common.Hello;

@Component
public class ConsumerHello {
  @RpcReference(microserviceName = "pojoc", schemaId = "hello")
  public Hello hello;
  
  public String hello(String name) {
    System.out.println("***********serverB try to visit serverC*****************");
    String result = hello.sayHi(name);
    System.out.println("serverB result: " + result);
    return result;
  }
}
