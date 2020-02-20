package servicecomb.springmvcserverc.java.training.designpattern.proxy.cglibproxy;

/**
 * Cglib代理(也叫作子类代理)
 * 静态代理和动态代理模式有个相同点就是都要求目标对象是实现一个接口的对象,
 * 然而并不是任何对象都会实现一个接口，也存在没有实现任何的接口的对象,
 *这时就可以使用继承目标类以目标对象子类的方式实现代理,这种方法就叫做:Cglib代理，也叫作子类代理，
 * 它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
 */
public class Client {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.random() * 1000;
        }
        ArraySort2 arraySort2 = (ArraySort2) CglibProxy.newProxyInstance(new ArraySort2());
        arraySort2.bubbleSort(arr);
        arraySort2.selectSort(arr);
        arraySort2.quickSort(arr);
    }
}
