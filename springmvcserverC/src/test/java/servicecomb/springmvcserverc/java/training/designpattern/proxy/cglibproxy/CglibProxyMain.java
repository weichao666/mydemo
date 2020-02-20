package servicecomb.springmvcserverc.java.training.designpattern.proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyMain {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.random() * 1000;
        }
        //实例化一个增强器，也就是cglib中的一个class generator
        Enhancer enhancer = new Enhancer();
        //设置目标类
        enhancer.setSuperclass(ArraySort2.class);
        //设置拦截对象，这里直接使用匿名内部类写法
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                String sortName = method.getName();
                switch (sortName) {
                    case "bubbleSort":
                        sortName = "冒泡排序";
                        break;
                    case "selectSort":
                        sortName = "选择排序";
                        break;
                    case "quickSort":
                        sortName = "快速排序";
                        break;
                    default:
                        break;
                }
                long start = System.currentTimeMillis();
                //此处一定要用proxy的invokeSuper方法来调用目标类的方法
                methodProxy.invokeSuper(object, args);
                long end = System.currentTimeMillis();
                System.out.println("本次" + sortName + "的执行时间为：" + (end - start) + "ms");
                return null;
            }
        });
        //生成代理类并返回一个实例
        ArraySort2 arraySort2 = (ArraySort2) enhancer.create();
        arraySort2.bubbleSort(arr);
        arraySort2.selectSort(arr);
        arraySort2.quickSort(arr);
    }
}
