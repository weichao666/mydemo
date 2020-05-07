package servicecomb.springmvcserverc.java.training.designpattern.proxy.dynamicproxy;

import servicecomb.springmvcserverc.java.training.designpattern.proxy.staticproxy.ConcreteSubject;
import servicecomb.springmvcserverc.java.training.designpattern.proxy.staticproxy.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerImplTest {
    public static void main(String[] args) {
        Subject concreteSubject = new ConcreteSubject();
        Subject subject = (Subject) Proxy.newProxyInstance(concreteSubject.getClass().getClassLoader(),
                concreteSubject.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //TODO 原对象方法调用前添加的预处理逻辑
                        System.out.println("预处理...");
                        Object result = null;
                        //调用目标方法
                        result = method.invoke(concreteSubject, args);
                        //TODO 原对象方法调用后添加的后处理逻辑
                        System.out.println("后处理...");
                        //返回的值是被代理接口的一个实现类
                        return result;
                    }
                });
        subject.request();
    }


}
