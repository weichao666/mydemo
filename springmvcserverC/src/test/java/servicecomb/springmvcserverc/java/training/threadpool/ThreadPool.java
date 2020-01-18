package servicecomb.springmvcserverc.java.training.threadpool;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws Exception {
        //一、开发者自定义线程池
        //ThreadPoolExecutor类，常用于自定义线程池
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());


        //二、使用自带的线程池样例，目的是简化操作
        //方法一：
        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        //只能有固定数目的活动线程存在，长连接

        //方法二：
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        //只能有一个线程，支持定时，可以用一个线程周期性执行任务，长连接

        //方法三：
        ExecutorService executorService4 = Executors.newCachedThreadPool();
        //执行生存期很短的异步型任务，默认周期是60s，超时线程实例将被终止及移出池。

        //方法四：
        ScheduledExecutorService executorService5 = Executors.newScheduledThreadPool(5);
        //线程可以按schedule依次delay执行，或周期执行，结合Timer

    }
}
