package servicecomb.springmvcserverc.java.training.designpattern.decorator;

/**
 * 饮料接口
 */
public interface Beverage {
    //返回商品描述
    public String getDescription();
    //返回价格
    public double getPrice();
}
