import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.servicecomb.foundation.common.net.IpPort;
import org.apache.servicecomb.foundation.common.net.NetUtils;
import org.springframework.util.ObjectUtils;

public class StringTest {
  private String defaultValue;
  public static void main(String[] args) {
    StringTest test = new StringTest();
    System.out.println(test.getDefaultValue());

    List<String> list = new ArrayList<>();
    System.out.println(ObjectUtils.isEmpty(list));
//    System.out.println(Array.getLength(list));
//    System.out.println("coll:" + Collections.list(null));

//    List<String> uriList = null;
//    ArrayList<IpPort> ipPortList = new ArrayList<>();
//    uriList.forEach(anUriList -> {
//      try {
//        URI uri = new URI(anUriList.trim());
//        boolean ssl = "https".equals(uri.getScheme());
//        ipPortList.add(NetUtils.parseIpPort(uri.getScheme(), uri.getAuthority()));
//      } catch (Exception e) {
//        System.out.println("servicecomb.service.registry.address invalid : {}");
//        e.printStackTrace();
//      }
//    });

    //string is null , then to string[]
    System.out.println("start");
    String url = null;
    String[] str = url.split(",");
    System.out.println("str:" + str);
  }

  public Object getDefaultValue() {
    if(defaultValue == null) {
      System.out.println("defaultValue is null");
      return null;
    }
    System.out.println("defaultValue is not null:" + defaultValue);
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }
}
