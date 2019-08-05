package servicecomb.springmvcserverc.java;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class TestProxy {
  @Test
  public void testProxy() {
    IHelloWorld hw = new HelloWorld();
    LoggerHandler handler = new LoggerHandler(hw);
    IHelloWorld proxy = (IHelloWorld) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), hw.getClass().getInterfaces(), handler);
    proxy.sayHello();
    proxy.sayHi();
  }

  @Test
  public void testProxy2() {
    LoggerHandler handler = new LoggerHandler();
    IHelloWorld proxy = (IHelloWorld) handler.newProxyInstance(new HelloWorld());
    System.out.println();
    proxy.sayHello();
    proxy.sayHi();
  }
}
