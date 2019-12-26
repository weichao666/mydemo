package servicecomb.springmvcserverc.java.training.thread;

public class TestThread {
  public static void main(String[] args) throws Exception {
    MyThread thread = new MyThread();
    new Thread(thread, "线程1").start();
    new Thread(thread, "线程2").start();
  }

  public static class MyThread extends Thread {
    private int total = 10;
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        synchronized (this) {
          if (total > 0) {
            try {
              Thread.sleep(100);
              System.out.println(Thread.currentThread().getName() + "卖票------>" + this.total--);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }
}
