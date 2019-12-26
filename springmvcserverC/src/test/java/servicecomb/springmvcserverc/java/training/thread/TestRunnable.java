package servicecomb.springmvcserverc.java.training.thread;

public class TestRunnable {
  public static void main(String[] args) throws Exception {
    MyRunnable myRunnable = new MyRunnable();
    new Thread(myRunnable, "线程1").start();
    new Thread(myRunnable, "线程2").start();
  }

  public static class MyRunnable implements Runnable {
    private int total = 10;

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        synchronized (this) {
          if (total > 0) {
            try {
              Thread.sleep(100);
              System.out.println(Thread.currentThread().getName() + "卖票----->" + this.total--);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }
}
