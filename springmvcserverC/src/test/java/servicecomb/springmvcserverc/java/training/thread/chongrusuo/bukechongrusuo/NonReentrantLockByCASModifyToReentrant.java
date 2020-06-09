package servicecomb.springmvcserverc.java.training.thread.chongrusuo.bukechongrusuo;

import com.google.common.util.concurrent.Atomics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 不可重入锁，通过CAS+自旋实现
 * 对于自旋锁来说：
 * 1、若有同一线程两次调用lock()，会导致第二次调用lock位置进行自旋，产生了死锁，
 * 说明这个锁并不是可重入的。（在lock函数内，应验证线程是否为已经获得锁的线程）
 * 2、若问题1已经解决，当unlock()第一次调用时，就已经将锁释放了。实际上不应释放锁。
 * 采用记数次进行统计，修改之后的效果见NonReentrantLockByCASModifyToReentrant类
 * 该自旋锁即为可重入锁
 */
public class NonReentrantLockByCASModifyToReentrant {
    private AtomicReference lockedThread = new AtomicReference();
//    private int count = 0;
    private AtomicInteger count2 = new AtomicInteger(0);

    public void lock() {
        Thread currentThread = Thread.currentThread();
        System.out.println("----");
        //如果当前线程获取过锁，计数器加一
        if (currentThread == lockedThread.get()) {
//            count++;
            count2.getAndIncrement();
            return;
        }
        //当lockedThread持有引用变量为null时，设置lockedThread持有引用为当前线程变量（初始化）
        //如果进来的不是当前线程（多线程情况），进入while自旋循环，直到lockedThread重新置为null，然后争夺锁
        while (!lockedThread.compareAndSet(null, currentThread)) {
            //自旋，空循环，等到锁被释放
        }
    }
    public void unlock() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == lockedThread.get()) {
//            if (count != 0) {
//                count--;
            if (0 != count2.get()) {
                count2.getAndDecrement();
            } else {
                //如果是本线程锁定的，可以成功释放锁
                //当前锁被同一个线程重入的其他所有次数都unlock后，把这个线程的最早一次进入时获取的锁进行释放，lockedThread置为null，让while循环里的其他线程进行抢夺
                lockedThread.compareAndSet(currentThread, null);
            }
        }
    }
}
