package servicecomb.springmvcserverc.java.training.thread;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用ExecutorService、Callable、Future实现有返回结果的线程
 * 有返回值的任务必须实现Callable接口
 * 无返回值的任务必须实现Runnable接口
 * 执行Callable任务后，可以获取一个Future对象，在该对象上调用get就可以获取到Callable任务返回的Object
 * 注意：get方法时阻塞的，即：线程无返回结果，get方法会一直等待
 */
public class TestCallableAndExecutorService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("---程序开始运行---");
//        Date date1 = new Date();

        int taskSize = 5;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //创建多个有返回值的任务
        List<Future> list = new ArrayList();
        try {

            for (int i = 0; i < taskSize; i++) {
                Callable c = new MyCallable(i + " ");
                //执行任务并获取future对象
                Future future = pool.submit(c);
                list.add(future);
            }
        } finally {
            // 关闭线程池
            pool.shutdown();
        }
        //获取所有并发任务的运行结果
        for (Future future : list) {
            //从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + future.get().toString());
        }

    }

    static class MyCallable implements Callable {
        private String taskNum;
        MyCallable(String taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public Object call() throws Exception {
            System.out.println(">>>" + taskNum + "任务启动");
            Date date1 = new Date();
            Thread.sleep(1000);
            Date date2 = new Date();
            long time = date2.getTime() - date1.getTime();
            System.out.println(">>>" + taskNum + "任务终止");
            return taskNum + "任务返回运行结果，当前任务时间【" + time + "毫秒】";
        }
    }
}
