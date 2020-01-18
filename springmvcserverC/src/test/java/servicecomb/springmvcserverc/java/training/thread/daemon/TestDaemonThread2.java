package servicecomb.springmvcserverc.java.training.thread.daemon;

import java.util.concurrent.TimeUnit;

public class TestDaemonThread2 {
  public static void main(String[] args) throws Exception{
    //设置一个钩子线程，在JVM退出时输出日志
    Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("The JVM exit success !!!")));
    //在主线程中，new一个非守护线程， 稍后把它设置成守护线程；
    Thread thread = new Thread(() -> {
      //模拟线程不退出的情况
      while (true) {
        try {
          TimeUnit.SECONDS.sleep(1);
          System.out.println("I am running ...");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    //将该线程设置为守护线程
    thread.setDaemon(true);
    //启动线程
    thread.start();
    TimeUnit.SECONDS.sleep(2);

    //主线程即将退出
    System.out.println("The main thread ready to exit...");
    //结果：当主线程退出时，JVM会随之退出运行，守护线程同时也会被回收，即使里面是个死循环也不碍事；
  }
}
