package servercomb.serverb;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;

public class PojoBMain {
  
  public static void main(String[] args) throws Exception {
    init();
  }

  private static void init() throws Exception {
    Log4jUtils.init();
    BeanUtils.init();
  }

}
