package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.product;

public class Product {
  private int count = 0; //商品数量
  private int MAX = 10; //最大库存

  public synchronized void makeProduct() throws Exception{
    String thread_name = Thread.currentThread().getName(); //获取生产者线程名
    if (count >= MAX) {
      System.out.println("货物已满，" + thread_name + "停止生产...");
      notifyAll(); // 唤醒所有的消费者线程
      wait(); // 生产者线程停止生产
    } else {
      Thread.sleep(1000);
      count++; // 生产者线程生产商品
      System.out.println(thread_name + "生产了产品，目前商品总量：" + count);
      notifyAll(); // 唤醒所有消费者线程，模拟消费
    }
  }

  public synchronized void buyProduct() throws Exception{

    String thread_name = Thread.currentThread().getName(); //获取消费者线程名
      if (count <= 0) {
        System.out.println("货物已卖完，等待进货中，" + thread_name + "停止购买");
        notifyAll(); // 唤醒生产者线程 生产商品
        wait(); // 消费者线程休眠，停止消费
      } else {
        Thread.sleep(1000);
        count--; //消费者购买了商品
        System.out.println(thread_name + "购买了一个产品，目前商品总量：" + count);
      }
  }
}
