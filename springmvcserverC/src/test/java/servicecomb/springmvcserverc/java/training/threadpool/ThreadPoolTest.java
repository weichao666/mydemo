package servicecomb.springmvcserverc.java.training.threadpool;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {
        //有界队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
        //放弃拒绝的任务并抛出异常
        //.AbortPolicy()以抛出一个RejectedExecutionException的方式处理拒绝执行的任务；
        RejectedExecutionHandler abortPolicyHandler = new ThreadPoolExecutor.AbortPolicy();
        //.DiscardPolicy()以直接忽略的方式处理拒绝执行的任务；
        RejectedExecutionHandler discardPolicyHandler = new ThreadPoolExecutor.DiscardPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                10,
                TimeUnit.SECONDS,
                workQueue,
                discardPolicyHandler);
        for (int i = 0; i < 30; i++) {
            threadPoolExecutor.execute(new MyTask());
            System.out.println("核心线程数：" + threadPoolExecutor.getCorePoolSize());
            System.out.println("最大线程数：" + threadPoolExecutor.getMaximumPoolSize());
            System.out.println("线程池数：" + threadPoolExecutor.getPoolSize());
            System.out.println("队列任务数：" + threadPoolExecutor.getQueue().size());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
        //shutdown()方法与shutdownNow()方法用于停掉线程池
        //首先调用shutdown()阻止新的任务提交到线程池，
        threadPoolExecutor.shutdown();
//        // 调用awaitTermination等待正在执行的线程执行完毕，或者超时或者被打断执行，
        if (threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
            // 然后再调用shutdownNow()取消当前正在执行的任务
            threadPoolExecutor.shutdownNow();
        }
        System.out.println("核心线程数：" + threadPoolExecutor.getCorePoolSize());
        System.out.println("最大线程数：" + threadPoolExecutor.getMaximumPoolSize());
        System.out.println("线程池数：" + threadPoolExecutor.getPoolSize());
        System.out.println("队列任务数：" + threadPoolExecutor.getQueue().size());
        //运行结果解释：
        //新提交一个任务时的处理流程很明显：
        //1、如果当前线程池的线程数还没有达到基本大小(poolSize < corePoolSize)，无论是否有空闲的线程新增一个线程处理新提交的任务；
        //2、如果当前线程池的线程数大于或等于基本大小(poolSize >= corePoolSize) 且任务队列未满时，就将新提交的任务提交到阻塞队列排队，等候处理workQueue.offer(command)；
        //3、如果当前线程池的线程数大于或等于基本大小(poolSize >= corePoolSize) 且任务队列满时；
        //3.1、当前poolSize<maximumPoolSize，那么就新增线程来处理任务；
        //3.2、当前poolSize=maximumPoolSize，那么意味着线程池的处理能力已经达到了极限，此时需要拒绝新增加的任务。至于如何拒绝处理新增的任务，取决于线程池的饱和策略RejectedExecutionHandler。
    }
    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", Hello");
        }
    }
}
