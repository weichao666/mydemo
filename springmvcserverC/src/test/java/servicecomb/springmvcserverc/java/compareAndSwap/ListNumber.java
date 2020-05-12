package servicecomb.springmvcserverc.java.compareAndSwap;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ListNumber {
//  NumberRise rise = new NumberRise();

  public class ThreadRunnable implements Runnable {
    @Override
    public void run() {
//      rise.rise();
      NumberRise.rise();
    }
  }

  @Test
  public void test(){
    ThreadRunnable tr1 = new ThreadRunnable();
    ThreadRunnable tr2 = new ThreadRunnable();
    Thread thread1 = new Thread(tr1);
    Thread thread2 = new Thread(tr2);
    thread1.run();
    thread2.run();
    System.out.println("rise count: " + NumberRise.count);
  }
}
