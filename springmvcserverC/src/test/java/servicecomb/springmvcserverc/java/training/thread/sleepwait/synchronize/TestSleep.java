package servicecomb.springmvcserverc.java.training.thread.sleepwait.synchronize;

import java.util.stream.Stream;

/**
 * sleep不依赖同步方法，wait需要
 *
 * testSleep()这个方法会依次运行，不会出现任何异常
 */
public class TestSleep {
    private final static Object lock = new Object();
    public static void main(String[] args) {
        Stream.of("线程一","线程二").forEach(n -> new Thread(n) {
            public void run() {
                TestSleep.testSleep();
            }
        }.start());
    }

    private static void testSleep() {
        System.out.println(Thread.currentThread().getName() + "休眠开始");
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "休眠结束");
    }
}
