package servicecomb.springmvcserverc.java.training.threadpool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 首先通过线程池提供的submit()方法或者execute()方法，要求线程池执行某个任务，线程池收到任务后，会有以下几种处理情况：
 * 1.当前线程池中运行的线程数量还没达到corePoolSize大小时，线程池会创建一个新的线程运行任务，无论之前已经创建的线程是否处于空闲状态；
 * 2.如果当前线程池中运行的线程数量已经达到设置的corePoolSize大小，线程池会把新的任务加入到等待队列中，直到某个线程空闲了，线程池会根据你设置的等待队列规则，从队列中取出一个新的任务执行；
 * 3.如果队列已满，这个任务无法加入等待队列，这时线程池就会创建一个“非核心线程”直接运行这个任务。你设置的大于corePoolSize参数小于maxinumPoolSize参数的部分，就是线程池可以临时创建的“非核心线程”的最大数量，
 *   这种情况下如果某个线程没有运行任何任务，在等待keepAliveTime时间后，这个线程将会被销毁，直到线程池的线程数量重新达到corePoolSize；
 * 4.如果这个任务，无法被“核心线程”直接执行，又无法加入等待队列，又无法创建“非核心线程”直接执行，且你没有为线程池设置RejectedExecutionHandler，这时线程池会抛出RejectedExecutionException异常，即线程池拒绝接受这个任务；
 *   实际上抛出异常的操作，是ThreadPoolExecutor线程池中一个默认的实现，也可以自己定义RejectedExecutionHandler
 * 5.一旦线程池中某个线程完成了任务的执行，它就会试图到任务等待队列中拿下一个等待任务；
 *
 */
public class ThreadPoolTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        int corePoolSize = 2;
        int maxinumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameThreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxinumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );
//        executor.prestartAllCoreThreads();  //预启动所有核心线程

        for (int i = 1; i <= 10; i++) {
            MyTask myTask = new MyTask(String.valueOf(i));
            executor.execute(myTask);
        }
        System.in.read();   //  阻塞主线程

    }
    public static class NameThreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + "has been created");
            return t;
        }
    }
    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r, executor);
        }

        private void doLog(Runnable r, ThreadPoolExecutor executor) {
            //可做日志记录等
            System.err.println(r.toString() + " rejected");
//            System.out.println("completedTaskCount: " + executor.getCompletedTaskCount());
        }
    }
    static class MyTask implements Runnable {
        private String name;
        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.toString() + " is running!");
            try {
                Thread.sleep(5000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
