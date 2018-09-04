package servercomb.serverb;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.apache.servicecomb.provider.pojo.RpcReference;
import servercomb.serverb.consumer.ConsumerHello;
import servicecomb.demo.common.Hello;

public class PojoBMain {
  
  public static void main(String[] args) throws Exception {
    init();
    consumerHello = BeanUtils.getBean("consumerHello");
    String result = consumerHello.hello("haha");
  }

  private static void init() throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
  }

  public static ConsumerHello consumerHello;

}
