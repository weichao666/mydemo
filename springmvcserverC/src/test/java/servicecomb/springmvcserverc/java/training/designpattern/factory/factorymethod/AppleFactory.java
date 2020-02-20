package servicecomb.springmvcserverc.java.training.designpattern.factory.factorymethod;

import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.IPhone;
import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.Phone;

public class AppleFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new IPhone();
    }
}
