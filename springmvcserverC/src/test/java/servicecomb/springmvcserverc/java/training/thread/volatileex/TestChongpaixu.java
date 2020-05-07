package servicecomb.springmvcserverc.java.training.thread.volatileex;

/**
 * 如代码由于(1)(2)(3)(4) 之间不存在依赖，所以写线程(3)(4)可能被重排序为先执行（4）再执行（3),
 * 那么执行（4）后，读线程可能已经执行了（1）操作，并且在（3）执行前开始执行（2）操作，这时候打印结果为0而不是4
 * 解决：使用volatile 修饰ready可以避免重排序
 */
public class TestChongpaixu {
    private static int num = 0;
    private static boolean ready = false;

    public static class ReadThread extends Thread {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {                                    //1
                    System.out.println(num + num);              //2
                } else {
                    System.out.println("not ready");
                }
                System.out.println("read thread...");
            }
        }
    }
    public static class WriteThread extends Thread {
        public void run() {
            num = 2;                                            //3
            ready = true;                                       //4
            System.out.println("writeThread set over...");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();
        WriteThread writeThread = new WriteThread();
        writeThread.start();

        Thread.sleep(10);
        readThread.interrupt();
        System.out.println("main end");
    }
}
