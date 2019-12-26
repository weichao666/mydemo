package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.breakfast;

// 生产者线程
public class Producer implements Runnable{
  private Breakfast breakfast;
  public Producer(Breakfast breakfast) {this.breakfast = breakfast;}

  @Override
  public void run() {
    try {
      for (int i = 1; i <= 7; i++) {
        if (i % 2 == 0) {
          this.breakfast.makeBreakfast("面包", "牛奶");
        } else {
          this.breakfast.makeBreakfast("馒头", "稀饭");
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
