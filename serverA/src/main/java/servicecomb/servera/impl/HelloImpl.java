package servicecomb.servera.impl;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.provider.pojo.RpcSchema;

import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;
import servicecomb.servera.consumer.ConsumerHello;

@RpcSchema(schemaId = "hello")
public class HelloImpl implements Hello{
  public static ConsumerHello consumerHello;

  public String sayHi(String name) {
    consumerHello = BeanUtils.getBean("consumerHello");
    String result = consumerHello.hello(name);
    return result;
  }

  public String sayHello(Person person) {
    
    return person.getName();
  }

}
