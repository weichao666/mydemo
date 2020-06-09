package servicecomb.springmvcserverc.java.training.thread.chongrusuo.kechongrusuo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockReentrant {
    public static void main(String[] args) {
        new Thread(new LockReentrant()).start();
    }
    static class LockReentrant implements Runnable {
        private final Lock lock = new ReentrantLock();

        /**
         * 方法1，调用方法2
         */
        public void method1() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "method1()");
            try {
                method2();
            } finally {
                lock.unlock();
            }

        }

        /**
         * 方法2，打印前获取lock锁
         * 如果同一线程，锁不可重入的话，method2需要等待method1释放lock锁
         */
        public void method2() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "method2()");
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void run() {
            method1();
        }
    }
}
