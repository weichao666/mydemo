package servicecomb.springmvcserverc.java;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestStringValueOf {

  @Test
  public void testStringNull() {
    String test = null;
    try {
      System.out.println(String.valueOf(test));
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  @Test
  public void testNull() throws NullPointerException{
    try {
      System.out.println(String.valueOf(null));
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  @Test
  public void testStringUtil() {
    System.out.println(StringUtils.trim(" ip = 10.10.10.10 "));
    System.out.println(StringUtils.contains("Huawei.com", "huawei"));
    String fullClass = "com.huawei.core.guide.SubString";
    System.out.println(StringUtils.substringAfterLast(fullClass, "."));
  }
}
