package servicecomb.springmvcserverc.java.training.thread.producerandconsumer.breakfast;

public class Breakfast {
  private String food; //吃的
  private String drink; //喝的
  private boolean haveThing = false;

  public synchronized void makeBreakfast(String food, String drink) throws Exception{
    if (haveThing) {
      wait(); //生产者线程进入同步对象维护的“线程等待池”
      //1.如果一个线程调用了wait()方法，那么该线程首先需要获取到这个对象的锁（换句话说，一个线程如果调用了某个方法的wait方法，那么该wait方法必须是在synchronized方法中的）
      //2.如果一个线程调用了wait()方法，那么当前线程就会释放掉线程的锁。（这个是wait和sleep方法不同的地方）
    }
    this.food = food;
    Thread.sleep(1000); //休眠，但不释放“锁”
    this.drink = drink;
    haveThing = true;
    notify();
    //在java中一个对象，会有两个池（锁池，等待池），如果一个线程调用了wait方法，那么该线程进入该对象的等待池中（释放锁），
    // 如果未来的某一刻，另外一个线程调用了这个对象的notify方法，或者notifyAll方法，那么在等待池中的线程就会起来进入该对象的锁池中，参与到获取锁的竞争当中，
    //如果获取锁成功，将沿着wait方法之后的代码执行
  }

  public synchronized void eatBreakfast() throws Exception{
    if (!haveThing) {
      wait(); //消费者线程进入同步对象维护的“线程等待池”，而且当前线程释放“锁”
    }
    Thread.sleep(1000);
    System.out.println("               " + this.food + "---------->" + this.drink);
    haveThing = false;
    notify();
  }
}
