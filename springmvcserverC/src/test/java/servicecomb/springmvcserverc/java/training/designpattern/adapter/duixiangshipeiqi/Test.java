package servicecomb.springmvcserverc.java.training.designpattern.adapter.duixiangshipeiqi;

/**
 * 对象适配器与类适配器不同之处在于，类适配器通过继承来完成适配，对象适配器则是通过关联来完成，
 * 这里稍微修改一下 Adapter 类即可将转变为对象适配器
 * 注意这里的 Adapter 是将 Adaptee 作为一个成员属性，而不是继承它
 */
public class Test {
    public static void main(String[] args) {
        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }
}
