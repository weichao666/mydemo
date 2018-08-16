package servicecomb.serverc.impl;

import org.apache.servicecomb.provider.pojo.RpcSchema;
import org.apache.servicecomb.swagger.invocation.context.ContextUtils;

import servicecomb.demo.bean.Person;
import servicecomb.demo.common.Hello;

@RpcSchema(schemaId = "hello")
public class HelloImpl implements Hello{

  public String sayHi(String name) {
    System.out.println("********serverC has been visited");
    String contextvalue = ContextUtils.getInvocationContext().getContext("try");
    System.out.println("try's contextvalue is : " + contextvalue);
    return name;
  }

  public String sayHello(Person person) {
    
    return person.getName();
  }

}
