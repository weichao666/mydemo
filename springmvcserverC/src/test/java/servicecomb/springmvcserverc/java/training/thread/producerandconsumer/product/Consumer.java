package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.product;

public class Consumer implements Runnable{
  private Product product;
  public Consumer(Product product) {this.product = product;}
  @Override
  public void run() {
    while (true) {
      try {
        product.buyProduct(); //调用消费方法
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
