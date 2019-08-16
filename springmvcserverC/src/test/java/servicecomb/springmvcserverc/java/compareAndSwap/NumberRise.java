package servicecomb.springmvcserverc.java.compareAndSwap;

public class NumberRise {
  public static int count =0;
  public static void rise() {
    for (int i = 0 ; i < 100; i++) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      count++;
    }
  }
  public int getCount() {
    return count;
  }
}
