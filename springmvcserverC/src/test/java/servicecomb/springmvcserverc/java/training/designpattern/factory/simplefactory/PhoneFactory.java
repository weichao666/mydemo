package servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory;

/**
 * 工厂类
 */
public class PhoneFactory {
    public static Phone makePhone(String phoneType) {
        Phone phone = null;
        if (phoneType.equalsIgnoreCase("MiPhone")) {
            phone = new MiPhone();
        }
        if (phoneType.equalsIgnoreCase("IPhone")) {
            phone = new IPhone();
        }
        return phone;
    }
}
