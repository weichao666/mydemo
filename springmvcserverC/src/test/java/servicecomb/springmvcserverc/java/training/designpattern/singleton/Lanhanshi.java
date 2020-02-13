package servicecomb.springmvcserverc.java.training.designpattern.singleton;

//懒汉式，线程安全
//懒加载模式，虽然做到了线程安全，并且解决了多实例的问题，但是它并不高效
//因为在任何时候只能有一个线程调用getLanhanshi()方法，但是同步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象时
//这就引出了双重检验锁。
public class Lanhanshi {
    private static Lanhanshi instance;
    /*
    构造器私有化，只有Lanhanshi类内才可以调用构造器
    */
    private Lanhanshi() {}

    public static synchronized Lanhanshi getInstance() {
        if (instance == null) {
            instance = new Lanhanshi();
        }
        return instance;
    }
}
