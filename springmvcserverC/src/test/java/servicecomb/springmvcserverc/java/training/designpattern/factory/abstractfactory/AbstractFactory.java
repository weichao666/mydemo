package servicecomb.springmvcserverc.java.training.designpattern.factory.abstractfactory;

import servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory.Phone;

/**
 * AbstractFactory类：增加PC产品制造接口
 */
public interface AbstractFactory {
    Phone makePhone();
    PC makePC();
}
