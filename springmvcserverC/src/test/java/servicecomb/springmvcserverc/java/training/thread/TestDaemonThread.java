package servicecomb.springmvcserverc.java.training.thread;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestDaemonThread {

  public static void main(String[] args) throws Exception {
    //1、在主线程中 new一个非守护线程
    Thread thread = new Thread(() -> {
      //2、模拟非守护线程不退出的情况
      while (true) {
        try {
          //睡眠一秒
          TimeUnit.SECONDS.sleep(1);
          System.out.println("I am running ...");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    //3、启动线程
    thread.start();
    TimeUnit.SECONDS.sleep(2);
    //4、主线程即将退出
    System.out.println("The main thread ready to exit...");
    //结果：主线程退出，JVM进程依然没有退出，非守护线程还在运行着；
  }
}
