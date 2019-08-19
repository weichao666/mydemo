package servicecomb.springmvcserverc.java.sellthread;

public class SellThread implements Runnable{
  private int i = 20;
  String key = "";

  @Override
  public void run() {
    while (true) {
      //使用synchronized来加锁
//      synchronized (key) {
        if (i > 0) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(Thread.currentThread().getName() + " sell " + i--);
        }
//      }
    }
  }
}
