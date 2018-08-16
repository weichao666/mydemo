package servicecomb.servera.consumer;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;
import org.springframework.stereotype.Component;

import servicecomb.demo.common.Hello;

@Component
public class ConsumerHello {
  @RpcReference(microserviceName = "pojob", schemaId = "hello")
  public Hello hello;
  
  public String hello(String name) {
    ContextUtils.getInvocationContext().addContext("try", "get");

    System.out.println("***********serverA try to visit serverB*****************");
    String result = hello.sayHi(name);
    System.out.println("serverA result: " + result);
    return result;
  }
}
