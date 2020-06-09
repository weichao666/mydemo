package servicecomb.springmvcserverc.java.training.thread.lock;

import org.apache.commons.lang.StringUtils;

public class Test {
    static Object o1 = new Object();
    static Object o2 = new Object();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1) {
                    System.out.println("线程1锁o1");
                    try {
                        Thread.sleep(1000);
                        synchronized (o2) {
                            System.out.println("线程1锁o2");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o2) {
                    System.out.println("线程2锁o2");
                    synchronized (o1) {
                        System.out.println("线程2锁o1");
                    }
                }
            }
        }).start();
    }
}
