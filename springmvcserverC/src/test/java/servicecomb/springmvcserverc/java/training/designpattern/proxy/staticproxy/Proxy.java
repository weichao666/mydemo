package servicecomb.springmvcserverc.java.training.designpattern.proxy.staticproxy;

/**
 * 代理类
 */
public class Proxy implements Subject {
    //要代理的实现类
    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }
    //预处理
    private void before() {
        System.out.println("预处理...");
    }
    //后处理
    private void after() {
        System.out.println("后处理...");
    }
}
