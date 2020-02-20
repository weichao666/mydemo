package servicecomb.springmvcserverc.java.training.designpattern.factory.factorymethod;

import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.MiPhone;
import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.Phone;

public class XiaoMiFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }
}
