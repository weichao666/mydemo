package servicecomb.springmvcserverc.java.training.designpattern.factory.abstractfactory;

import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.MiPhone;
import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.Phone;

/**
 * XiaoMiFactory类：增加小米PC的制造（ConcreteFactory1）
 */
public class XiaoMiFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }

    @Override
    public PC makePC() {
        return new MiPC();
    }
}
