package servicecomb.springmvcserverc.java.training.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//tryLock()的使用方法
public class TestTryLock {
    private List list = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();

    public void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName() + "获得了锁");
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                lock.unlock();
                System.out.println(thread.getName() + "释放了锁");
            }

        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }

    public static void main(String[] args) {
        final TestTryLock testTryLock = new TestTryLock();
        new Thread(() -> {testTryLock.insert(Thread.currentThread());}).start();
        new Thread(() -> {testTryLock.insert(Thread.currentThread());}).start();
    }
}
