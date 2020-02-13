package servicecomb.springmvcserverc.java.training.designpattern.decorator;

/**
 * Soy——给咖啡加入豆浆
 */
public class Soy extends Decorator {
    private String description = "加入豆浆";
    private Beverage beverage;
    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription() + description;
    }
    @Override
    public double getPrice() {
        return beverage.getPrice() + 40;        //40表示豆浆的价格
    }
}
