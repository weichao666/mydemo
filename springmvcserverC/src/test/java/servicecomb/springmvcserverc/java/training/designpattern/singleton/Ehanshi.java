package servicecomb.springmvcserverc.java.training.designpattern.singleton;

//单例的饿汉式
//特点：在第一次加载类到内存中时就会创建对象,所以创建实例本身是线程安全的；
//好处：类，只会加载一次，所以这种写法可以保证对象的唯一性；
//弊端：因为类加载的时候就会创建对象，所以有的时候还不需要使用对象，就会创建对象，造成内存的浪费；
//这种写法如果完美的话，就没必要在啰嗦那么多双检锁的问题了。缺点是它不是一种懒加载模式（lazy initialization），
// 单例会在加载类后一开始就被初始化，即使客户端没有调用 getInstance()方法。
// 饿汉式的创建方式在一些场景中将无法使用：譬如 Singleton 实例的创建是依赖参数或者配置文件的，在 getInstance() 之前必须调用某个方法设置参数给它，那样这种单例写法就无法使用了。
public class Ehanshi {
    /*
    构造器私有化，只有Ehanshi类内才可以调用构造器
     */
    private Ehanshi() {}
    /*
    利用静态变量来记录Ehanshi的唯一实例
    直接初始化静态变量，这样就可以确保线程安全了
     */
    private static Ehanshi instance = new Ehanshi();

    public static Ehanshi getInstance() {
        return instance;
    }
}
