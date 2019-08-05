package servicecomb.springmvcserverc.java;

public class HelloWorld implements IHelloWorld {
  @Override
  public void sayHello() {
    System.out.println("hello world");
  }

  @Override
  public void sayHi() {
    System.out.println("hi everyone");
  }
}
