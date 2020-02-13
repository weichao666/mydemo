package servicecomb.springmvcserverc.java.training.designpattern.decorator;

/**
 * Mocha——给咖啡加入摩卡
 */
public class Mocha extends Decorator {
    private String description = "加入摩卡";
    private Beverage beverage;
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + "\n" + description;
    }
    @Override
    public double getPrice() {
        return beverage.getPrice() + 30;        //30表示摩卡的价格
    }
}

