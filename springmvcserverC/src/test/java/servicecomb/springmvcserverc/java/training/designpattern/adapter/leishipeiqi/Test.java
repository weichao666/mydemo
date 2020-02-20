package servicecomb.springmvcserverc.java.training.designpattern.adapter.leishipeiqi;

/**
 * 类适配器
 * 首先有一个已存在的将被适配的类Adaptee，定义一个目标接口Target，
 * 怎么才可以在目标接口中的 request() 调用 Adaptee 的 adapteeRequest() 方法呢？
 * 如果直接实现 Target 是不行的
 * 如果通过一个适配器类，实现 Target 接口，同时继承了 Adaptee 类，然后在实现的 request() 方法中调用父类的 adapteeRequest() 即可实现
 * 这样我们即可在新接口 Target 中适配旧的接口或类
 */
public class Test {
    public static void main(String[] args) {
        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
