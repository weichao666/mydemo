package servicecomb.springmvcserverc.java.sellthread;

import java.util.concurrent.atomic.AtomicInteger;

public class SellThread2 implements Runnable{
//  private int i = 20;
  private AtomicInteger count = new AtomicInteger(20);

  @Override
  public void run() {
    while (true) {
      //使用AtomicInteger属于原子操作，用compareAndSet来操作加减，不需要加锁
      int current = count.get();
      int next = current - 1;
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (count.compareAndSet(current, next) && (next >= 0)) {
        System.out.println(Thread.currentThread().getName() + " sell " + current);
      }
    }
  }
}
