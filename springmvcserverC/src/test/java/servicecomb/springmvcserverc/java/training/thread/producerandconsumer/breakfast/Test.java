package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.breakfast;

public class Test {
  public static void main(String[] args) throws Exception {
    Breakfast breakfast = new Breakfast();
    new Thread(new Producer(breakfast)).start(); //启动生产者线程
    new Thread(new Consumer(breakfast)).start(); //启动消费者线程
  }
}
