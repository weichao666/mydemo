package servicecomb.springmvcserverc.java.training;

import org.junit.Test;

public class TestHash {
  @Test
  public void testHash () {
    String str1 = "通话";
    String str2 = "重地";
    System.out.println(String.format("str1：%d | str2：%d", str1.hashCode(), str2.hashCode()));
    System.out.println(str1.equals(str2));
  }
}
