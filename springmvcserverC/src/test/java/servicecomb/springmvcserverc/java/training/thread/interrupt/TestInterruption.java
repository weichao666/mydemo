package servicecomb.springmvcserverc.java.training.thread.interrupt;

//interrupt函数，它可以迅速中断被阻塞的线程，抛出一个InterruptedException
public class TestInterruption implements Runnable{
    private volatile static boolean on = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new TestInterruption(), "InterruptionInJava");
        testThread.start();
        Thread.sleep(2000);
        TestInterruption.on = true;
        testThread.interrupt();

        System.out.println("main end");
    }

    @Override
    public void run() {
        while(!on) {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                System.out.println("caught exception right now : " + e);
            }
        }
    }
}
