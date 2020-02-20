package servicecomb.springmvcserverc.java.training.designpattern.factory.factorymethod;

/**
 * 工厂方法模式(Factory Method)
 * 和简单工厂模式中工厂负责生产所有产品相比，工厂方法模式将生成具体产品的任务分发给具体的产品工厂
 * 也就是定义一个抽象工厂，其定义了产品的生产接口，但不负责具体的产品，将生产任务交给不同的派生类工厂。
 * 这样不用通过指定类型来创建对象了
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory miFactory = new XiaoMiFactory();
        AbstractFactory appleFactory = new AppleFactory();
        miFactory.makePhone();                  // make xiaomi phone!
        appleFactory.makePhone();               // make iphone!
    }
}
