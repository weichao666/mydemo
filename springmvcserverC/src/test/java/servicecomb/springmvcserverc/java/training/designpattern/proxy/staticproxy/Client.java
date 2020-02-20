package servicecomb.springmvcserverc.java.training.designpattern.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Proxy proxy = new Proxy(subject);
        proxy.request();
    }
}
