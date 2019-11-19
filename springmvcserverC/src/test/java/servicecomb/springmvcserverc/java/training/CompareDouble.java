package servicecomb.springmvcserverc.java.training;

import org.junit.Test;

public class CompareDouble {
  @Test
  public void compareDouble () {
    //在比较两个双精度（double）类型的数值时，ε 设置为 1E-14,而在比较两个单精度（float）类型的数值时，ε 设置为 1E-7
    final double EPSILON = 1E-14;
    double x = 1.0 - 0.9;
    double y = 0.1;
    System.out.println(x - y == 0);
    System.out.println(Math.abs(1 - 0.8 - 0.2) < EPSILON);
    double m = 0;
    System.out.println(m == 0);

  }
}
