package servicecomb.springmvcserverb;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.springframework.beans.factory.annotation.Autowired;

import servicecomb.springmvcserverb.consumer.ConsumerHello;

public class SpringMvcBMain {
  
  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
//    ConsumerHello consumerHello = new ConsumerHello();
    
//    String result = consumerHello.sayHi("hello");
//    String result2 = consumerHello.sayHi("hello");
  }

}
