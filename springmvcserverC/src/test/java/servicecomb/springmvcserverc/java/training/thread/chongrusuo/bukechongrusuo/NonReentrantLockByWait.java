package servicecomb.springmvcserverc.java.training.thread.chongrusuo.bukechongrusuo;

public class NonReentrantLockByWait {
    //是否被锁
    private volatile boolean locked = false;

    //加锁
    public synchronized void lock() throws InterruptedException {
        //当某个线程获取锁成功，其他线程进入等待状态
        while (locked) {
            wait();
        }
        //加锁成功，locked设置为true
        locked = true;
    }
    //释放锁
    public synchronized void unlock() {
        locked = false;
        notify();
    }
}
