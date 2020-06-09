package servicecomb.springmvcserverc.java.training.thread.chongrusuo.bukechongrusuo;

/**
 * 测试不可重入锁修改为可重入锁
 */
public class TestReentrantByWait {
    public static void main(String[] args) {
        new Thread(new LockNonReentrant()).start();
    }
    static class LockNonReentrant implements Runnable {
        private final NonReentrantLockByWaitModifyToReentrant lock = new NonReentrantLockByWaitModifyToReentrant();

        /**
         * 方法1，调用方法2
         */
        public void method1() throws InterruptedException {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " method1()");
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
        public void method2() throws InterruptedException {
            System.out.println("method2()");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " method2()");
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void run() {
            //线程启动，执行方法1
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
