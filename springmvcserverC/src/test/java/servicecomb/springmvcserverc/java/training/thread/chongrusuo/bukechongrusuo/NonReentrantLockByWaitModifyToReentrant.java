package servicecomb.springmvcserverc.java.training.thread.chongrusuo.bukechongrusuo;

/**
 *
 */
public class NonReentrantLockByWaitModifyToReentrant {
    //是否被锁
    private volatile boolean isLocked = false;
    //本地锁标志位
    Thread localThread = null;
    int localCount = 0;

    //加锁
    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        //当某个线程获取锁成功，其他线程进入等待状态
        while (isLocked && localThread != thread) {
            wait();
        }
        //加锁成功，locked设置为true
        isLocked = true;
        localCount++;
        localThread = thread;
    }
    //释放锁
    public synchronized void unlock() {
        if (Thread.currentThread() == this.localThread) {
            localCount--;
            if (localCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
