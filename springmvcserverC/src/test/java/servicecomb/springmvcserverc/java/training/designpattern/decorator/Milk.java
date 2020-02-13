package servicecomb.springmvcserverc.java.training.designpattern.decorator;

import com.google.j2objc.annotations.ObjectiveCName;

/**
 * Milk——具体装饰类，给咖啡加入牛奶
 */
public class Milk extends Decorator {
    private String description = "加入牛奶";
    private Beverage beverage;
    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + "\n" + description;
    }
    @Override
    public double getPrice() {
        return beverage.getPrice() + 20;        //20表示牛奶的价格
    }
}
