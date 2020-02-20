package servicecomb.springmvcserverc.java.training.designpattern.factory.factorymethod;

import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.Phone;

//抽象工厂
public interface AbstractFactory {
    Phone makePhone();
}
