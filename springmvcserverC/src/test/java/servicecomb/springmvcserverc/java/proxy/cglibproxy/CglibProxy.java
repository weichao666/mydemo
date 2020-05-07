package servicecomb.springmvcserverc.java.proxy.cglibproxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JDK动态代理和CGLIB字节码生成的区别？
 * （1）JDK动态代理只能对实现了接口的类生成代理，而不能针对类
 * （2）CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
 *  因为是继承，所以该类或方法最好不要声明成final
 */
//servicecomb.springmvcserverc.java.training.designpattern.proxy.cglibproxy也使用了cglib，只不过使用的spring包中的cglib
// 使用spring包中cglib，其实和引单独的cglib包是一样，只不过spring为了版本不冲突，将cglib包含在自己的包中。
public class CglibProxy implements MethodInterceptor {
//    private Object target;
    //重写拦截方法
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        //如果使用method.invoke()方法，则必须创建本类的target变量并赋值，invoke方法里必须传target参数
        //如果使用methodProxy.invokeSuper()，则不能使用本类的target变量，必须使用父类的object
//        Object invoke = method.invoke(target, args);     //方法执行，参数：target 目标对象 args参数数组
        Object invoke = methodProxy.invokeSuper(object, args);
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }

    public Object getCglibProxy(Object objectTarget) {
        //为目标对象target赋值
//        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass());    //设置父类，因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setCallback(this);                         //设置回调
        Object result = enhancer.create();                  //创建并返回代理对象
        return result;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        HelloWorld helloWorld = (HelloWorld) cglibProxy.getCglibProxy(new HelloWorld());
        helloWorld.sayHello();
    }
}
