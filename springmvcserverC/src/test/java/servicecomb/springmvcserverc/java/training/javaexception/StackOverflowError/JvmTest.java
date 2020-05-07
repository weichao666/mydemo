package servicecomb.springmvcserverc.java.training.javaexception.StackOverflowError;

//栈溢出(StackOverflowError)
//栈是线程私有的，他的生命周期与线程相同，每个方法在执行的时候都会创建一个栈帧，用来存储局部变量表，操作数栈，动态链接，方法出口灯信息。
// 局部变量表又包含基本数据类型，对象引用类型（局部变量表编译器完成，运行期间不会变化）
//所以我们可以理解为栈溢出就是方法执行时创建的栈帧超过了栈的深度。那么最有可能的就是方法递归调用产生这种结果
//使用参数 -Xss 去调整JVM栈的大小，-Xss2M
public class JvmTest {
    private int i = 0;
    public void a() {
        System.out.println(i++);
        a();
    }
    public static void main(String[] args) {
        JvmTest j = new JvmTest();
        j.a();
    }
}
