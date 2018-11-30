import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.ObjectUtils;

public class StringTest {
  private String defaultValue;
  public static void main(String[] args) {
    StringTest test = new StringTest();
    System.out.println(test.getDefaultValue());

    List<String> list = new ArrayList<>();
    System.out.println(ObjectUtils.isEmpty(list));
    System.out.println(Array.getLength(list));
    System.out.println("coll:" + Collections.list(null));
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
