package servicecomb.springmvcserverc.java.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//JDK动态代理
//动态代理类只能代理接口（不支持抽象类），代理类都需要实现InvocationHandler类，实现invoke方法。
//被代理对象targetObject通过参数传递进来，我们通过targetObject.getClass().getClassLoader()获取ClassLoader对象，然后通过targetObject.getClass().getInterfaces()获取它实现的所有接口，然后将targetObject包装到实现了InvocationHandler接口的LoggerHandler对象中。通过newProxyInstance函数我们就获得了一个动态代理对象。
public class LoggerHandler implements InvocationHandler {
  private Object target;

  public LoggerHandler() {};

  public LoggerHandler(Object target) {
    this.target = target;
  }

  //该invoke方法就是调用被代理接口的所有方法时需要调用的，该invoke方法返回的值是被代理接口的一个实现类
  //关联的这个实现类的方法被调用时将被执行
  /*InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数*/
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.getName().equals("sayHello")) {
      System.out.println("prepare to invoke sayHello method");
      //调用目标方法
      Object result = method.invoke(target, args);
      System.out.println("sayHello method invoke end");
      return result;
    }
    if (method.getName().equals("sayHi")) {
      System.out.println("prepare to invoke sayHi method");
      //调用目标方法
      Object result = method.invoke(target,args);
      System.out.println("sayHi method invoke end");
      return result;
    }
    return null;
  }

  //也可把Proxy这个工具类写在InvocationHandler处理类里，单独写一个方法。
  public Object newProxyInstance(Object target) {
    this.target = target;
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }
}
