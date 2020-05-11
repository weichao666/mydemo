package servicecomb.springmvcserverc.java.training.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口通过FutureTask包装器来创建Thread线程
 */
public class TestCallableAndFuture {
  public static void main(String[] args) throws Exception {
    MyCallable myCallable = new MyCallable();
    FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
    new Thread(futureTask, "有返回值的线程").start();
    System.out.println("子线程的返回值" + futureTask.get());
  }

  public static class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
      int i;
      for (i = 0; i < 10; i++) {
        System.out.println(Thread.currentThread().getName() + " " + i);
      }
      return i;
    }
  }
}
