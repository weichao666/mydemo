package servicecomb.springmvcserverc.java.training.thread.lock;

import servicecomb.springmvcserverc.java.training.thread.producerandconsumer.product.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//lockInterruptibly()响应中断的使用方法
public class TestLockInterruptiby {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        TestLockInterruptiby testLockInterruptiby = new TestLockInterruptiby();
        MyThread myThread1 = new MyThread(testLockInterruptiby);
        MyThread myThread2 = new MyThread(testLockInterruptiby);
        myThread1.start();
        myThread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread2.interrupt();
        //运行之后，发现thread2能够被正确中断
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName() + "获得了锁");
            long startTime = System.currentTimeMillis();
            for (;;) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }

    static class MyThread extends Thread {
        private TestLockInterruptiby testLockInterruptiby = null;
        public MyThread(TestLockInterruptiby testLockInterruptiby) {
            this.testLockInterruptiby = testLockInterruptiby;
        }
        @Override
        public void run() {
            try {
                testLockInterruptiby.insert(Thread.currentThread());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
            }
        }
    }
}
