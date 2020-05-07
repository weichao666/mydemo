package servicecomb.springmvcserverc.java.training.designpattern.proxy.cglibproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

//如果使用了匿名内部类，就不用单独实现MethodInterceptor
public class InterceptorImpl implements MethodInterceptor {
    @Override
    public Object intercept(Object object, java.lang.reflect.Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //TODO 原对象方法调用前添加的预处理逻辑
        String sortName = method.getName();
        long start = System.currentTimeMillis();

        //此处一定要用proxy的invokeSuper方法来调用目标类的方法
        Object obj = methodProxy.invokeSuper(object, args);

        //TODO 原对象方法调用后添加的后处理逻辑
        long end = System.currentTimeMillis();
        System.out.println("本次" + sortName + "的执行时间为：" + (end - start) + "ms");
        return obj;
    }
}
