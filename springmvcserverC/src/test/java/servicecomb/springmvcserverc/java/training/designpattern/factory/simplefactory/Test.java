package servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory;

/**
 * 简单工厂模式
 * 该模式通过向工厂传递类型来指定要创建的对象
 */
public class Test {
    public static void main(String[] args) {
        Phone phone = PhoneFactory.makePhone("IPhone");    // make iphone!
        Phone phone1 = PhoneFactory.makePhone("MiPhone");  // make xiaomi phone!

    }
}
