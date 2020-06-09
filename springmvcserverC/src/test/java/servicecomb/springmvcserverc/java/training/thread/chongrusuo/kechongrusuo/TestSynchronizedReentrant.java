package servicecomb.springmvcserverc.java.training.thread.chongrusuo.kechongrusuo;

/**
 * 可重入锁：指同一个线程在外层方法获取锁的时候，进入内层方法会自动获取锁
 * 为了避免死锁的发生，JDK中基本都是可重入锁
 * 测试synchronized加锁可重入性
 */
public class TestSynchronizedReentrant {
    public static void main(String[] args) {
        TestSynchronizedReentrant sr = new TestSynchronizedReentrant();
        SynchronizedReentrant sr1 = sr.new SynchronizedReentrant();
        new Thread(sr1).start();
    }

    class SynchronizedReentrant implements Runnable {
        private final Object object = new Object();

        /**
         * 方法1，调用方法2
         */
        public void method1() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " method1()");
                method2();
            }
        }

        /**
         * 方法2，打印前获取object锁
         * 如果同一线程，锁不可重入的话，method2需要等待method1释放object锁
         */
        public void method2() {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " method2()");
            }
        }

        @Override
        public void run() {
            //线程启动，执行方法1
            method1();
        }
    }
}
