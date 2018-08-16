package servicecomb.springmvcserverc;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringMvcCMain {

  public static void main(String[] args) throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
    Logger LOGGER = LoggerFactory.getLogger(SpringMvcCMain.class);
//    int i = 0;
//    while (true) {
//      LOGGER.info("{}", i++);
//      Thread.sleep(10);
//    }
//    System.exit(0);
  }

}
