package servicecomb.springmvcserverc.java.training.threadpool;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        List<String> outputList = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> threads = Stream.generate(() -> new Thread(new Worker(outputList, countDownLatch)))
                .limit(5)
                .collect(Collectors.toList());
        threads.forEach(Thread::start);
        //CountDownLatch.await() 方法在倒计数为0之前会阻塞当前线程
        countDownLatch.await();
        outputList.add("Latch released");
        System.out.println(outputList.toString());
    }

    public static class Worker implements Runnable {
        private List<String> outputList;
        private CountDownLatch countDownLatch;
        public Worker(List<String> outputList, CountDownLatch countDownLatch) {
            this.outputList = outputList;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            outputList.add("Counted down");
            // 每个独立子线程执行完后,countDownLatch值减1
            countDownLatch.countDown();
        }

    }
}
