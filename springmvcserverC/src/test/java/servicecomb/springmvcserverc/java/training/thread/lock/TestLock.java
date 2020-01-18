package servicecomb.springmvcserverc.java.training.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//lock()的使用方法
public class TestLock {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    //我们可以在创建ReentrantLock对象时，通过以下方式来设置锁的公平性：ReentrantLock lock = new ReentrantLock(true);
    //如果参数为true表示为公平锁，为fasle为非公平锁。默认情况下，如果使用无参构造器，则是非公平锁
    private Lock lock = new ReentrantLock(true);

    public void insert(Thread thread) {

        lock.lock();
        try {
            System.out.println(thread.getName() + "获得了锁");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }

    public static void main(String[] args) {
        final TestLock testLock = new TestLock();
        //匿名内部类
        new Thread(() -> testLock.insert(Thread.currentThread())).start();
        new Thread(() -> testLock.insert(Thread.currentThread())).start();
    }

}
