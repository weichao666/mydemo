package servicecomb.springmvcserverc.java.training.designpattern.singleton;

//枚举单例
//创建枚举默认就是线程安全的，所以不需要担心double checked locking，而且还能防止反序列化导致重新创建新的对象
//枚举不怕反射攻击
public class Meiju {
    //私有化构造函数
    private Meiju() {};

    //对外暴露一个获取Meiju对象的静态方法
    public static Meiju getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    //定义一个静态枚举类
    static enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private Meiju instance;
        //私有化枚举的构造函数
        private SingletonEnum() {
            instance = new Meiju();
        }
        public Meiju getInstance() {
            return instance;
        }
    }
}

