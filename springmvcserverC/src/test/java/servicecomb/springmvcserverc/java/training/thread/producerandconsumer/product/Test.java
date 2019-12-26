package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.product;

public class Test {
  public static void main(String[] args) throws Exception {
    Product product = new Product();
    new Thread(new Producer(product), "生产者1号-->").start();
    new Thread(new Producer(product), "生产者2号-->").start();
    new Thread(new Producer(product), "生产者3号-->").start();
    new Thread(new Producer(product), "生产者4号-->").start();
    new Thread(new Consumer(product), "消费者A-->").start();
    new Thread(new Consumer(product), "消费者B-->").start();
    new Thread(new Consumer(product), "消费者C-->").start();
    new Thread(new Consumer(product), "消费者D-->").start();
    new Thread(new Consumer(product), "消费者E-->").start();
  }
}
