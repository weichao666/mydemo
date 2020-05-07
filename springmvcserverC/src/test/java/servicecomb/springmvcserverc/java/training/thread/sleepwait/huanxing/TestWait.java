package servicecomb.springmvcserverc.java.training.thread.sleepwait.huanxing;

/**
 * sleep不需要被唤醒，wait如果没指定时间，就需要唤醒
 */
public class TestWait {
    private final static Object lock = new Object();
    private static void testWait() {
        synchronized (lock) {
            System.out.println("我一直在等待");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait被唤醒了");
        }
    }
    private static void notifyWait() {
        synchronized (lock) {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.notify();
            System.out.println("休眠5秒钟唤醒wait");
        }
    }

    public static void main(String[] args) {
        new Thread() {
            public void run() {
                TestWait.testWait();
            }
        }.start();
        new Thread() {
            public void run() {
                TestWait.notifyWait();
            }
        }.start();
    }
}
