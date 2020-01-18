package servicecomb.springmvcserverc.java.training.number;

import java.util.Scanner;

import org.junit.Test;

public class NumberConvert {
  //n进制小数
  //将任意十进制正小数m分别转换成2,3,4,5,6,7,8,9 n进制正小数，小数点后保留8位，并输出
  @Test
  public void numberConvert() {
    double m = 0.795000;
    int n;
    for (n = 2; n <= 9; n++) {
      conversion(m, n);
    }
  }

  private void conversion(double m, int n) {
    System.out.printf("十进制正小数 %f 转换成 %d 进制数为：0.", m, n);
    //小数点后保留8位，保存至数组中
    int[] result = new int[8];
    int count;
    double s = m;
    for (count = 0; count < 8; count++) {
      s *= n;
      result[count] = (int) s;
      s -= result[count];
    }
    for (int i = 0; i < 8; i++) {
      System.out.printf("%d", result[i]);
    }
    System.out.println();
  }
}
