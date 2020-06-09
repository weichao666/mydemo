package servicecomb.springmvcserverc.java.training.thread.zuseduilie;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueModeFir {
    //使用list实现阻塞队列
    private List queue = new LinkedList();
    //有界队列上限
    private int limit = 10;
    //阻塞队列构造
    public BlockingQueueModeFir(int limit) {
        this.limit = limit;//指定队列上限
    }

    /**
     * 向阻塞队列中添加元素
     */
    public synchronized void enqueue(Object item) throws InterruptedException {
        //阻塞队列中元素数已达上限，则执行添加元素的线程阻塞
        while (this.queue.size() == this.limit) {
            wait();
        }
        //阻塞队列已被清空时，唤醒所有线程
        if (this.queue.size() == 0) {
            notifyAll();
        }
        //阻塞队列存在元素且元素数量未达上限，向队列中添加元素
        this.queue.add(item);
        System.out.println(item + "  " + this.queue.size());
    }

    /**
     * 从阻塞队列取出元素
     * @return
     * @throws InterruptedException
     */
    public synchronized Object dequeue() throws InterruptedException {
        //当阻塞队列为空时，无法取出元素，执行等待
        while (this.queue.size() == 0) {
            wait();
        }
        //当阻塞队列已满时，唤醒所有线程
        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        //阻塞队列存在元素且元素数量未达上限，取出队列中的元素
        return this.queue.remove(0);
    }
}
