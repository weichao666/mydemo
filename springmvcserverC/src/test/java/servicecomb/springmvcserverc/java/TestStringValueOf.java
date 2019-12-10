package servicecomb.springmvcserverc.java;

import static org.junit.Assert.*;

import java.util.Arrays;

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

  @Test
  public void testStringMethods() {
    String str = " abcdEfg ";
    System.out.println(str.indexOf("E"));
    System.out.println(str.charAt(4));
    System.out.println(str.replace("bc", "cb"));
    System.out.println(str.trim());
    System.out.println(str);
    System.out.println(Arrays.toString(str.split("d")));
    System.out.println(Arrays.toString(str.split("", 4)));
    System.out.println(Arrays.toString(str.getBytes()));
    System.out.println(str.length());
    System.out.println(str.toCharArray());
    System.out.println(str.toLowerCase());
    System.out.println(str.toUpperCase());
    System.out.println(str.substring(1,3));
    System.out.println(str.equals("ab"));
  }
}
