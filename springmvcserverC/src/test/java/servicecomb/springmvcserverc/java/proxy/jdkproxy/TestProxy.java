package servicecomb.springmvcserverc.java.proxy.jdkproxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

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
    //方式二：将工具类Proxy.newProxyInstance写在LoggerHandler里
    IHelloWorld proxy = (IHelloWorld) handler.newProxyInstance(new HelloWorld());
    System.out.println();
    proxy.sayHello();
    proxy.sayHi();
  }
}
