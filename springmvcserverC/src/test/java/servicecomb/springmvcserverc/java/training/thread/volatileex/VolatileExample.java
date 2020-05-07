package servicecomb.springmvcserverc.java.training.thread.volatileex;

/**
 * 1、13行什么也不写，程序始终也不会结束，其原因是线程的run方法未结束，即run方法中的flag仍然为false
 * 2、如果把flag加上volatile修饰符，结果是程序正常退出，volatile生效了；
 * 3、我们再修改一下。去掉volatile关键字，恢复到起始的例子，然后把while(!flag){}改为while(!flag){System.out.println(1);}，
 * 再执行一下看看。按分析，没有volatile关键字的时候，程序不会执行结束，虽然加上了打印语句，但没有做任何的关键字/逻辑的修改，应该程序也不会结束才对，
 * 但执行结果却是：程序正常结束。
 *
 * 如今的volatile例子已经很难重现，如只有在while死循环时才体现出volatile的作用，
 * 哪怕只是加了System.out.println(1)这么一小段，普通变量也能达到volatile的效果，这是什么原因呢？
 * 原来只有在对变量读取频率很高的情况下，虚拟机才不会及时回写主内存，而当频率没有达到虚拟机认为的高频率时，普通变量和volatile是同样的处理逻辑，
 * 如在每个循环中执行System.out.println(1)加大了读取变量的时间间隔，使虚拟机认为读取频率并不那么高，所以实现了和volatile一样的效果。
 *
 * volatile的效果在jdk1.2及之前很容器重现，但随着虚拟机的不断优化，如今的普通变量的可见性已经不是那么严重的问题了
 *
 * volatile的适用场景
 * 并发专家建议我们远离volatile是有道理的，这里再总结一下：
 * * volatile是在synchronized性能低下的时候提出的。如今synchronized的效率已经大幅提升，所以volatile存在的意义不大。
 * * 如今非volatile的共享变量，在访问不是超级频繁的情况下，已经和volatile修饰的变量有同样的效果了。
 * * volatile不能保证原子性，这点是大家没太搞清楚的，所以很容易出错。
 * * volatile可以禁止重排序。
 *
 * 所以如果我们确定能正确使用volatile，那么在禁止重排序时是一个较好的使用场景，否则我们不需要再使用它。
 */
public class VolatileExample extends Thread {
    //设置类静态变量，各线程访问这同一共享变量
    private static boolean flag = false;

    //无限循环，等待flag变为true时才跳出循环
    public void run() {
        while (!flag) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileExample().start();
        //sleep的目的是等待线程启动完毕，也就是说进入run的无限循环体了
        Thread.sleep(100);
        flag = true;
    }
}
