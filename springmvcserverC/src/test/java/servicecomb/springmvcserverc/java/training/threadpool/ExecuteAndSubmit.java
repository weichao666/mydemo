package servicecomb.springmvcserverc.java.training.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteAndSubmit {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        //匿名内部类
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("execute任务执行中");
            }
        });
        //Lambda表达式，进一步简化匿名内部类的书写
        Future<String> future = executorService.submit(() -> {
            System.out.println("submit任务执行中");
            return "submit执行完毕，这是执行的返回结果";
        });
        try {
            System.out.println(future.get());
            //如果传参是Callable，则future.get()返回是期望值，表明任务完成
            System.out.println(future.isDone());
            //如果传参是Runnable，则future.get()返回null，表明任务完成，不过一般是通过future.isDone()来判断是否执行成功。
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("任务失败原因：" + e.getCause().getMessage());
        }
        executorService.shutdown();
    }
    //submit()有返回值。
    //execute没有返回值。
    //submit()方便做异常处理。通过Future.get()可捕获异常。
}
