package servicecomb.springmvcserverc.java.training.classtest.parentchildren;

public class B extends A {
    public static String b = printStr("子类的静态变量");
    public String str = printStr("子类的非静态变量");
    static {
        printStr("子类的静态块");
    }
    {
        printStr("子类的非静态块");
    }
    public B() {
        printStr("子类的构造方法");
    }

    public static void main(String[] args) {
        new B();
        A[] a = new A[10];

    }
}
