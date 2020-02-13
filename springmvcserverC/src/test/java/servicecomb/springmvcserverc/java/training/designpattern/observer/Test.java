package servicecomb.springmvcserverc.java.training.designpattern.observer;

/*
观察者模式：定义一对多的关系，让多个观察对象同时监听某一个主题对象，主题对象状态发生变化就通知所有观察者对象。
所以它是由两类对像组成：Subject主题+Observer观察者。主题发布事件，观察者通过订阅事件观察主题。
Subject和Observer之间存在依赖关系，存在耦合。
观察者模式中，目标对象也就是Subject管理观察者，发布-订阅模式中多了一个中间层通道
 */
//观察者模式和发布-订阅者模式之间的区别
//1.Observer模式，观察者是知道Subject主题的，目标主题一直保持对观察者的记录，
//而publish-subscribe模式中，订阅者和发布者互相不知道对方，通过消息代理进行通信。
//2.Observer模式中，观察者和主题之间存在以来耦合关系，而发布订阅者模式则完全松耦合。
//3.多数情况下，Observer模式是同步，例如事件触发，而发布-订阅者使用的消息队列模式，大多数处理异步事件。
public class Test {
    public static void main(String[] args) {
        XiaoMei xiaoMei = new XiaoMei();
        XiaoLi xiaoLi = new XiaoLi();
        XiaoWang xiaoWang = new XiaoWang();

        //将观察者添加进被观察者的队列中
        xiaoMei.addPerson(xiaoLi);
        xiaoMei.addPerson(xiaoWang);

        //小美向小王和小李发送通知
        xiaoMei.notifyPerson();

    }
}
