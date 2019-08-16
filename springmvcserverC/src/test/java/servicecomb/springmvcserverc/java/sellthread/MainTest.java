package servicecomb.springmvcserverc.java.sellthread;

public class MainTest {
  public static void main (String[] args) {
    SellThread sell = new SellThread();
    Thread sell1 = new Thread(sell, "sellman1");
    Thread sell2 = new Thread(sell, "sellman2");
    Thread sell3 = new Thread(sell, "sellman3");
    sell1.start();
    sell2.start();
    sell3.start();
    System.out.println("end");
  }
}
