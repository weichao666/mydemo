package servicecomb.springmvcserverc.java.training.thread.chongrusuo.bukechongrusuo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 不可重入锁，通过CAS+自旋实现
 * 对于自旋锁来说：
 * 1、若有同一线程两次调用lock()，会导致第二次调用lock位置进行自旋，产生了死锁，
 * 说明这个锁并不是可重入的。（在lock函数内，应验证线程是否为已经获得锁的线程）
 * 2、若问题1已经解决，当unlock()第一次调用时，就已经将锁释放了。实际上不应释放锁。
 * （采用记数次进行统计，修改之后的效果见NonReentrantLockByCASModifyToReentrant类）
 */
public class NonReentrantLockByCAS {
    private AtomicReference lockedThread = new AtomicReference();

    public void lock() {
        Thread currentThread = Thread.currentThread();
        System.out.println("----");
        //当lockedThread持有引用变量为null时，设置lockedThread持有引用为当前线程变量
        while (!lockedThread.compareAndSet(null, currentThread)) {
            //自旋，空循环，等到锁被释放
        }
    }
    public void unlock() {
        //如果是本线程锁定的，可以成功释放锁
        lockedThread.compareAndSet(Thread.currentThread(), null);
    }
}
