package servicecomb.springmvcserverc.java.training.thread.sleepwait.synchronize;

import java.util.stream.Stream;

/**
 * sleep不依赖同步方法，wait需要
 *
 * 启动程序后会报错Exception in thread "线程一" Exception in thread "线程二" java.lang.IllegalMonitorStateException
 * 也就是启用的两个线程都出现了这个非法monitor状态异常
 */
public class TestWait {
    private final static Object lock = new Object();
    public static void main(String[] args) {
        Stream.of("线程一","线程二").forEach(n -> new Thread(n) {
            public void run() {
                TestWait.testWait();
            }
        }.start());
    }

    private static void testWait() {
        try {
            lock.wait(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "wait结束");
    }
}
