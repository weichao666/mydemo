package servicecomb.springmvcserverc.java.training.thread.interrupt;

/**
 * 当线程中为了等待一些特定条件的到来时候，一般会调用Thread.sleep(),wait,join方法在阻塞当前线程，比如sleep(3000);
 * 那么到3s后才会从阻塞下变为激活状态，但是有可能在在3s内条件已经满足了，
 * 这时候可以调用该线程的interrupt方法，sleep方法会抛出InterruptedException异常，线程恢复激活状态
 * 如果抛出 InterruptedException那么就意味着抛出异常的方法是阻塞方法，比如Thread.sleep,wait,join
 */
public class SleepInterrupt implements Runnable{

    @Override
    public void run() {
        System.out.println("thread-sleep for 2000 seconds");
        try {
            Thread.sleep(2000000);
            System.out.println("thread waked up");
        } catch (InterruptedException e) {
            System.out.println("thread-interrupted while sleeping");
            return;
        }
        System.out.println("thread-leaving normally");
    }

    public static void main(String[] args) throws InterruptedException {
        SleepInterrupt sleepInterrupt = new SleepInterrupt();
        Thread thread = new Thread(sleepInterrupt);
        thread.start();

        //主线程休眠两秒，从而确保刚才启动的线程有机会执行一段时间
        Thread.sleep(2000);
        System.out.println("main() - interrupting other thread");
        //中断线程thread
        thread.interrupt();

        System.out.println("main() - leaving");
    }
}
