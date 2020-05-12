package servicecomb.springmvcserverc.java.training.designpattern.proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib代理(也叫作子类代理)
 * 静态代理和动态代理模式有个相同点就是都要求目标对象是实现一个接口的对象,
 * 然而并不是任何对象都会实现一个接口，也存在没有实现任何的接口的对象,
 *这时就可以使用继承目标类以目标对象子类的方式实现代理,这种方法就叫做:Cglib代理，也叫作子类代理，
 * 它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
 */
//servicecomb.springmvcserverc.java.proxy.cglibproxy也使用了cglib，是原生的cglib
//这里使用spring包中cglib，其实和引单独的cglib包是一样，只不过spring为了版本不冲突，将cglib包含在自己的包中。
public class CglibProxy {
    public static Object newProxyInstance(Object target) {
        //实例化一个增强器，也就是cglib中的一个class generator
        Enhancer enhancer = new Enhancer();
        //设置目标类
        enhancer.setSuperclass(target.getClass());
        //设置拦截对象，这里直接使用匿名内部类写法
//        enhancer.setCallback(new InterceptorImpl());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //TODO 原对象方法调用前添加的预处理逻辑
                String sortName = method.getName();
                long start = System.currentTimeMillis();

                //此处一定要用proxy的invokeSuper方法来调用目标类的方法
                methodProxy.invokeSuper(object, args);

                //TODO 原对象方法调用后添加的后处理逻辑
                long end = System.currentTimeMillis();
                System.out.println("本次" + sortName + "的执行时间为：" + (end - start) + "ms");
                return null;
            }
        });
        //生成代理类并返回一个实例
        return enhancer.create();
    }
}
