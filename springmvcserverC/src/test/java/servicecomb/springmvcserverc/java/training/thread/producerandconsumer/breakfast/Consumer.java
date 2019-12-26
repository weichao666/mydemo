package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.breakfast;

public class Consumer implements Runnable{

  private Breakfast breakfast;
  public Consumer(Breakfast breakfast) {this.breakfast = breakfast;}
  @Override
  public void run() {
    try {
      for (int i = 1; i <= 7; i++) {
        System.out.println("星期" + i + "早餐种类：food======>drink");
        this.breakfast.eatBreakfast();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
