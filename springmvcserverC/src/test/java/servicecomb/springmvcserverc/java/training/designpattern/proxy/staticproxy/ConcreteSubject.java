package servicecomb.springmvcserverc.java.training.designpattern.proxy.staticproxy;

/**
 * 具体主题类
 */
public class ConcreteSubject implements Subject {
    /**
     * 具体的业务逻辑实现
     */
    @Override
    public void request() {
        System.out.println("具体的业务...");
    }
}
