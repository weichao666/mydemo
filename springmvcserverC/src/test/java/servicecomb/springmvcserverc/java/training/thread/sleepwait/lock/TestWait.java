package servicecomb.springmvcserverc.java.training.thread.sleepwait.lock;

import java.util.stream.Stream;

/**
 * sleep不释放lock，wait会释放
 *
 * 线程一先抢到CPU资源，然后等待五秒
 * 在线程一wait的时候，就意味着线程一释放了锁，线程二可以进入
 */

public class TestWait {
    private final static Object lock = new Object();
    public static void main(String[] args) {
        Stream.of("线程一", "线程二").forEach(n -> new Thread(n) {
            public void run() {
                TestWait.testWait();
            }
        }.start());
    }
    private static void testWait() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "正在执行");
            try {
                lock.wait(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "wait结束");
        }
    }
}
