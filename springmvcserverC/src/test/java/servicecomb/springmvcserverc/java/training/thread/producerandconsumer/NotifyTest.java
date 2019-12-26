package servicecomb.springmvcserverc.java.training.thread.producerandconsumer;

public class NotifyTest {
  public static void main(String[] args) throws Exception {
    final NotifyTest test = new NotifyTest();
    for (int i = 0; i < 5; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          test.testWait();
        }
      }).start();
    }
    Thread.sleep(1000);
    synchronized (test) {
      test.notify();
    }
    Thread.sleep(3000);
    System.out.println("-------------------我是分割线---------------------");
    synchronized (test) {
      test.notifyAll();
    }
    //在notify方法调用时，只会去唤醒等待池中的一个线程，notifyAll是会唤醒所有的等待线程
    //如果没有被唤醒的线程，将一直处于等待池中，由于我们的线程没有设置成daemon线程，所以处于等待池中的线程如果一直没有被唤醒，那么我们的程序将不会停止。
    //1、如果一个线程调用了wait方法，那么该线程首先需要获取到这个对象的锁（换句话说，一个线程如果调用了某个方法的wait方法，那么该wait方法必须是在synchronized方法中的）
    //2、如果一个线程调用了wait方法，那么当前线程就会释放掉线程的锁。（这个是wait和sleep方法不同的地方）
    //3、在java中一个对象，会有两个池（锁池，等待池），如果一个线程调用了wait方法，那么该线程进入该对象的等待池中（释放锁），如果未来的某一刻，另外一个线程调用了这个对象的notify方法，或者notifyAll,那么在等待池中的线程就会起来进入该对象的锁池中，参与到获取锁的竞争当中，如果获取锁成功，将沿着wait方法之后的代码执行（这就是为什么生产者消费者中，使用while来判断状态，而不是if）。
    //notify和notifyAll方法并不会去释放当前锁对象，而是通过moniterexist来释放，也就是说，当前所述的代码块在执行结束之后，回去释放掉锁，只有在锁被释放掉之后，等待池中的线程进入到锁池，去竞争锁资源。
  }

  public synchronized void testWait() {
    System.out.println(Thread.currentThread().getName() + "Start----");
    try {
      wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "End------");
  }
}
