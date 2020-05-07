package servicecomb.springmvcserverc.java.training.thread.sleepwait.lock;

import java.util.stream.Stream;

/**
 * sleep不释放lock，wait会释放
 *
 * 线程一和线程二谁先抢到CPU资源，谁就一下子执行完，在sleep结束之后，第二个线程才会被执行
 */
public class TestSleep {
    private final static Object lock = new Object();
    public static void main(String[] args) {
        Stream.of("线程1","线程2").forEach(n -> new Thread(n) {
            public void run() {
                TestSleep.testSleep();
            }
        }.start());
    }

    //sleep方法休眠之后
    private static void testSleep() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "正在执行");
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "休眠结束");
        }
    }
}
