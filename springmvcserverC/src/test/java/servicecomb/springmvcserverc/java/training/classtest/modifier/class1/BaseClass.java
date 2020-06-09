package servicecomb.springmvcserverc.java.training.classtest.modifier.class1;

public class BaseClass {
    public int aa;              //可以被所有的类访问
    protected boolean bb;       //可以被所有子类以及本包的类使用

    void cc() {                 //default 访问权限，能在本包范围内使用
        System.out.println("包访问权限");
    }
    //private权限的内部类，即这是私有的内部类，只能在本类使用
    private class InnerClass{

    }
}
