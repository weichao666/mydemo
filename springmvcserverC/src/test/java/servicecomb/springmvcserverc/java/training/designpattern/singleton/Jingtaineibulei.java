package servicecomb.springmvcserverc.java.training.designpattern.singleton;

//静态内部类模式
//静态内部类的优点是：外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。
// 即当Jingtaineibulei第一次被加载时，并不需要去加载SingleTonHolder，只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE,
// 第一次调用getInstance()方法会导致虚拟机加载SingleTonHoler类，这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
//
//那么，静态内部类又是如何实现线程安全的呢？
//当getInstance()方法被调用时，SingleTonHolder才在Jingtaineibulei的运行时常量池里，把符号引用替换为直接引用，这时静态对象INSTANCE也真正被创建，
// 然后再被getInstance()方法返回出去，这点同饿汉模式。
//
// 那么INSTANCE在创建过程中又是如何保证线程安全的呢？
//虚拟机会保证一个类的<clinit>()方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的<clinit>()方法，其他线程都需要阻塞等待，直到活动线程执行<clinit>()方法完毕
//故而，可以看出INSTANCE在创建过程中是线程安全的，所以说静态内部类形式的单例可保证线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。

//那么，是不是可以说静态内部类单例就是最完美的单例模式了呢？其实不然，静态内部类也有着一个致命的缺点，就是传参的问题，
// 由于是静态内部类的形式去创建单例的，故外部无法传递参数进去，例如Context这种参数，
// 所以，我们创建单例时，可以在静态内部类与DCL(双重锁懒汉模式(Double Check Lock))模式里自己斟酌。

public class Jingtaineibulei {
    private static class SingletonHolder {
        private static final Jingtaineibulei INSTANCE = new Jingtaineibulei();
    }
    private Jingtaineibulei() {}
    public static Jingtaineibulei getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
