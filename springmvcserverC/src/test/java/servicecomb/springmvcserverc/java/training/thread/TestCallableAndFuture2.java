package servicecomb.springmvcserverc.java.training.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class TestCallableAndFuture2 {
  public static void main(String[] args) throws Exception {
    //匿名内部类实现Callable接口创建子线程
    Callable<Integer> myCallable = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        Thread.sleep(6000);
        return new Random().nextInt();
      }
    };

    FutureTask<Integer> future = new FutureTask<>(myCallable);
    new Thread(future).start();

    Thread.sleep(1000);
    System.out.println("hello begin");
    System.out.println(future.isDone());
//    future.cancel(true);
    if (!future.isCancelled()) {
      System.out.println(future.get());
      System.out.println(future.isDone());
      System.out.println("hello end");
    } else {
      System.out.println("cancel~");
    }
    //多线程返回执行结果是很有用的一个特性，Callable+Future/FutureTask可以获取多线程运行的结果，可以在等待时间太长没获取到需要的数据的情况下取消该线程的任务，非常有用。
  }
}
