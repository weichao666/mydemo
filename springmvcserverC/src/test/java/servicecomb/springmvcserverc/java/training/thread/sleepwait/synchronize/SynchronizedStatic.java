package servicecomb.springmvcserverc.java.training.thread.sleepwait.synchronize;

public class SynchronizedStatic {
    public synchronized static void m4() {
        System.out.println("m4 " + Thread.currentThread().getName());
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m5() {
        System.out.println("m5 " + Thread.currentThread().getName());
        try {
            Thread.sleep(5_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SynchronizedStatic sychronizedStatic = new SynchronizedStatic();
        new Thread("T4") {
            @Override
            public void run() {
                SynchronizedStatic.m4();
            }
        }.start();

        new Thread("T5") {
            @Override
            public void run() {
                SynchronizedStatic.m5();
            }
        }.start();
    }
}
