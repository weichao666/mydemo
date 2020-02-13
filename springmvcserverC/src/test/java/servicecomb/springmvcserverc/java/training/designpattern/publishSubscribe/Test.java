package servicecomb.springmvcserverc.java.training.designpattern.publishSubscribe;

import com.google.common.eventbus.EventBus;

/*
发布-订阅者模式
完全解耦，发布者和订阅者彼此不知道对方的存在，二者共享一个自定义事件的名称。它的优点非常明显，一为时间上的解耦，二为对象之间的解耦。
观察者模式中，目标对象也就是Subject管理观察者，发布-订阅模式中多了一个中间层通道
 */
//观察者模式和发布-订阅者模式之间的区别
//1.Observer模式，观察者是知道Subject主题的，目标主题一直保持对观察者的记录，
//而publish-subscribe模式中，订阅者和发布者互相不知道对方，通过消息代理进行通信。
//2.Observer模式中，观察者和主题之间存在以来耦合关系，而发布订阅者模式则完全松耦合。
//3.多数情况下，Observer模式是同步，例如事件触发，而发布-订阅者使用的消息队列模式，大多数处理异步事件。
public class Test {
    public static void main(String[] args) {
        EventListener listener1 = new EventListener();
        EventListener2 listener2 = new EventListener2();
        EventBus eventBus = new EventBus();
        eventBus.register(listener1);
        eventBus.register(listener2);

        eventBus.post(new Event("100"));
        eventBus.post(new Event("200"));
        eventBus.unregister(listener1);
        eventBus.post(new Event("300"));
    }
}
