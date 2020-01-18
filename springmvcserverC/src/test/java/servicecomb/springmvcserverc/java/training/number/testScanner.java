package servicecomb.springmvcserverc.java.training.number;

import java.util.Scanner;

import org.junit.Test;

public class testScanner {
  @Test
  public void testScanner () {
    System.out.println("请输入数字：");
    //输入包含两个数m,n，用空格隔开。输入包含多组测试，当m,n都为0时输入结束。
    //输入样例
    //0.795 3
    //0 0
    //输出样例
    //0.2101101122
    Scanner s = new Scanner(System.in);
    double m = 0;
    int n = 0;
    double m1;
    int n1;
    while (s.hasNext()) {
      m1 = s.nextDouble();
      n1 = s.nextInt();
      if (m1 == 0 && n1 ==0) {
        break;
      }
      m = m1;
      n = n1;
    }
    //编写程序实现将任意10进制正小数m转换成n进制的正小数，小数点后保留10位小数。
    int[] result = new int[10];
    int count;
    for (count = 0; count < 10; count++) {
      m *= n;
      result[count] = (int) m;
      m -= result[count];
    }
    // please define the JAVA output here. For example: System.out.println(s.nextInt());
    System.out.print("0.");
    for (int i = 0; i < 10; i++) {
      System.out.printf("%d", result[i]);
    }
  }
}
