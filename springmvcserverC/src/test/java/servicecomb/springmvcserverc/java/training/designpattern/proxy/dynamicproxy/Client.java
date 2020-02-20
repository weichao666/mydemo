package servicecomb.springmvcserverc.java.training.designpattern.proxy.dynamicproxy;

import servicecomb.springmvcserverc.java.training.designpattern.proxy.staticproxy.ConcreteSubject;
import servicecomb.springmvcserverc.java.training.designpattern.proxy.staticproxy.Subject;

public class Client {
    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler();
        Subject subject = (Subject) proxyHandler.newProxyInstance(new ConcreteSubject());
        subject.request();
    }
}
