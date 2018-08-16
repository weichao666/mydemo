package servercomb.serverb.impl;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;

import servercomb.serverb.consumer.ConsumerHello;
import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;

@RpcSchema(schemaId = "hello")
public class HelloImpl implements Hello{
  public static ConsumerHello consumerHello;

  public String sayHi(String name) {
    String contextvalue = ContextUtils.getInvocationContext().getContext("try");
    System.out.println("try's contextvalue is : " + contextvalue);
    consumerHello = BeanUtils.getBean("consumerHello");
    String result = consumerHello.hello(name);
    return result;
  }

  public String sayHello(Person person) {
    
    return person.getName();
  }

}
