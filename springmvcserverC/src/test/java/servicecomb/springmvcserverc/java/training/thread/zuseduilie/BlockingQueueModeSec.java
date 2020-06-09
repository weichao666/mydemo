package servicecomb.springmvcserverc.java.training.thread.zuseduilie;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueModeSec {
    public static void main(String[] args) {
        //缓冲区允许存放3个元素
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);

        //开启两个线程向队列中存入数据
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while(true) {
                    try {
                        Thread.sleep((long) (Math.random()*1000));
                        System.out.println(Thread.currentThread().getName() + "准备放数据"
                                + (queue.size() == 3?"..队列已满，正在等待":"..."));
                        queue.put(1);
                        System.out.println(Thread.currentThread().getName() + "存入数据，"
                                + "队列目前有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "准备取数据"
                                + (queue.size() == 0?"..队列已空，正在等待":"..."));
                        queue.take();
                        System.out.println(Thread.currentThread().getName() + "取出数据，"
                                + "队列目前有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
