package servicecomb.springmvcserverc.java.training.thread;

public class TestVolatile {
    public static void main(String[] args) throws Exception{
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(1000);
        runThread.setRunning(false);
        //现在有两个线程，一个是main线程，另一个是RunThread。它们都试图修改isRunning变量。
        // 按照JVM内存模型，main线程将isRunning读取到本地线程内存空间，修改后，再刷新回主内存
        //而在JVM设置成 -server模式运行程序时，线程会一直在私有堆栈中读取isRunning变量。
        // 因此，RunThread线程无法读到main线程改变的isRunning变量。从而出现了死循环，导致RunThread无法终止。
    }

    public static class RunThread extends Thread {
        //如果不加volatile，RunThread线程并不会终止，无法识别到isRunning已修改
        volatile private boolean isRunning = true;

        public void setRunning(boolean running) {
            isRunning = running;
        }

        @Override
        public void run() {
            System.out.println("进入到run方法中了");
            while (isRunning == true) {

            }
            System.out.println("线程执行完成了");
        }
    }
}
