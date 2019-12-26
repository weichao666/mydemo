package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.product;

public class Producer implements Runnable{
  private Product product;
  public Producer(Product product) {this.product = product;}
  @Override
  public void run() {
    while (true) {
      try {
        product.makeProduct(); // 调用生产方法
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
