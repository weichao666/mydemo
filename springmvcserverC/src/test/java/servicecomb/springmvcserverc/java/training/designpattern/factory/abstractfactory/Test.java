package servicecomb.springmvcserverc.java.training.designpattern.factory.abstractfactory;

/**
 * 抽象工厂模式(Abstract Factory)
 * 上面两种模式不管工厂怎么拆分抽象，都只是针对一类产品Phone（AbstractProduct），如果要生成另一种产品PC，应该怎么表示呢？
 * 抽象工厂模式通过在AbstarctFactory中增加创建产品的接口，并在具体子工厂中实现新加产品的创建，当然前提是子工厂支持生产该产品。否则继承的这个接口可以什么也不干。
 */
public class Test {
    public static void main(String[] args) {
        AbstractFactory miFactory = new XiaoMiFactory();
        AbstractFactory appleFactory = new AppleFactory();

        miFactory.makePC();
        miFactory.makePhone();

        appleFactory.makePC();
        appleFactory.makePhone();
    }
}
