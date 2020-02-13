package servicecomb.springmvcserverc.java.training.designpattern.decorator;

/**
 * CoffeeBean2——具体被装饰的对象类2
 */
public class CoffeeBean2 implements Beverage {
    private String description = "选了第二种咖啡豆";
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return 100;
    }
}
