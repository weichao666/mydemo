package servicecomb.springmvcserverc.java.training.designpattern.factory.abstractfactory;

import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.IPhone;
import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.MiPhone;
import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.Phone;

/**
 * AppleFactory类：增加苹果PC的制造（ConcreteFactory2）
 */
public class AppleFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new IPhone();
    }

    @Override
    public PC makePC() {
        return new MAC();
    }
}
