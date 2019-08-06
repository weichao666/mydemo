package servicecomb.springmvcserverc.java.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

import servicecomb.springmvcserverc.java.proxy.HelloWorld;
import servicecomb.springmvcserverc.java.proxy.IHelloWorld;
import servicecomb.springmvcserverc.java.proxy.LoggerHandler;

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
