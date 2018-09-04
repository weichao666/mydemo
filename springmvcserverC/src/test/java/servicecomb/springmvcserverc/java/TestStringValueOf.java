package servicecomb.springmvcserverc.java;

import static org.junit.Assert.*;

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
  public void testNull() {
    try {
      System.out.println(String.valueOf(null));
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
