package servicecomb.springmvcserverc.java.training.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReentrantReadWriteLock {
    //我们可以在创建ReentrantLock对象时，通过以下方式来设置锁的公平性：ReentrantLock lock = new ReentrantLock(true);
    //如果参数为true表示为公平锁，为fasle为非公平锁。默认情况下，如果使用无参构造器，则是非公平锁
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    public static void main(String[] args) {
        final TestReentrantReadWriteLock testReentrantReadWriteLock = new TestReentrantReadWriteLock();
        new Thread(() -> testReentrantReadWriteLock.get(Thread.currentThread())).start();
        new Thread(() -> testReentrantReadWriteLock.get(Thread.currentThread())).start();
        //运行结果说明thread1和thread2在同时进行读操作，这样就大大提升了读操作的效率
    }

    public void get(Thread thread) {
        reentrantReadWriteLock.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 50) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作结束");
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
