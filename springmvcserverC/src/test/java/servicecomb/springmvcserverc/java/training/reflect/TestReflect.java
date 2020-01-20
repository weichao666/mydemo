package servicecomb.springmvcserverc.java.training.reflect;

import servicecomb.demo.bean.ReflectStudent;
import servicecomb.demo.bean.ReflectStudent;

import java.lang.reflect.*;

//使用反射机制的一些地方：
//1.工厂模式：Factory类中用反射的话，添加了一个新的类之后，就不需要再修改工厂类Factory了
//2.数据库JDBC中通过Class.forName(Driver).来获得数据库连接驱动
//3.分析类文件：毕竟能得到类中的方法等等
//4.访问一些不能访问的变量或属性：破解别人代码
public class TestReflect {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
        //Demo1. 通过Java反射机制得到类的包名和类名
        Demo1();
        System.out.println("===============================================");

        //Demo2.  验证所有的类都是Class类的实例对象
        Demo2();
        System.out.println("===============================================");

        //Demo3.  通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]，无参构造
        Demo3();
        System.out.println("===============================================");

        //Demo4:  通过Java反射机制得到一个类的构造函数，并实现构造带参实例对象
        Demo4();
        System.out.println("===============================================");

        //Demo5:  通过Java反射机制操作成员变量, set 和 get
        Demo5();
        System.out.println("===============================================");

        //Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
        Demo6();
        System.out.println("===============================================");

        //Demo7: 通过Java反射机制调用类中方法
        Demo7();
        System.out.println("===============================================");

        //Demo8: 通过Java反射机制获得类加载器
        Demo8();
        System.out.println("===============================================");
    }

    /**
     * Demo1: 通过Java反射机制得到对象的包名和类名
     */
    private static void Demo1() {
        ReflectStudent reflectStudent = new ReflectStudent();
        System.out.println("Demo1: 包名：" + reflectStudent.getClass().getPackage().getName() + ", " +
                "完整类名：" + reflectStudent.getClass().getName());
    }

    /**
     * Demo2: 验证所有的类都是Class类的实例对象
     * @throws ClassNotFoundException
     */
    private static void Demo2() throws ClassNotFoundException {

        //定义两个类型都未知的Class，设置初值为null，看看如何给它们赋值为ReflectStudent类
        Class<?> class1 = null;
        Class<?> class2 = null;

        //写法一，可能抛出ClassNotFoundException（多用这个写法）
        class1 = Class.forName("servicecomb.demo.bean.ReflectStudent");
        System.out.println("Demo2:（写法1）包名：" + class1.getPackage().getName() + ", " +
                "完整类名：" + class1.getName());
        //写法二
        class2 = ReflectStudent.class;
        System.out.println("Demo2:（写法2）包名：" + class2.getPackage().getName() + ", " +
                "完整类名：" + class2.getName());
    }

    /**
     * Demo3: 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private static void Demo3() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> class1 = null;
        class1 = Class.forName("servicecomb.demo.bean.ReflectStudent");
        //由于这里不能带参数，所以你要实例化的这个类ReflectStudent，一定要有无参构造函数哈～
        ReflectStudent reflectStudent = (ReflectStudent) class1.newInstance();
        reflectStudent.setAge(20);
        reflectStudent.setName("xiaoming");
        System.out.println("Demo3: " + reflectStudent.getName() + reflectStudent.getAge());
    }

    /**
     * Demo4: 通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static void Demo4() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> class1 = null;
        ReflectStudent reflectStudent1 = null;
        ReflectStudent reflectStudent2 = null;

        class1 = Class.forName("servicecomb.demo.bean.ReflectStudent");
        //得到一系列构造函数集合
        Constructor<?>[] constructors = class1.getConstructors();
        reflectStudent1 = (ReflectStudent) constructors[0].newInstance();
        reflectStudent1.setName("xiaoming");
        reflectStudent1.setAge(20);

        reflectStudent2 = (ReflectStudent) constructors[1].newInstance("xiaohong", 21);
        System.out.println("Demo4: " + reflectStudent1.getName() + " : " + reflectStudent1.getAge() + " , "
                            + reflectStudent2.getName() + " : " + reflectStudent2.getAge());
    }

    /**
     * Demo5: 通过Java反射机制操作成员变量, set 和 get
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     */
    private static void Demo5() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> class1 = null;
        class1 = Class.forName("servicecomb.demo.bean.ReflectStudent");
        Object obj = class1.newInstance();

        Field field = class1.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj, "xiaoming");
        System.out.println("Demo5: 修改属性之后得到属性变量的值: " + field.get(obj));
    }

    /**
     * Demo6: 通过Java反射机制得到类的一些属性： 类中成员变量，类中方法，父类，继承的接口
     * @throws ClassNotFoundException
     */
    private static void Demo6() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("servicecomb.demo.bean.ReflectStudent");

        //取得类中成员变量
        Field[] fields = class1.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println("Demo6: 类中的成员：" + fields[i]);
        }
        System.out.println("===============================================");

        //取得类中方法
        Method[] methods = class1.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println("Demo6, 取得ReflectStudent类的方法：");
            System.out.println("函数名：" + methods[i].getName());
            System.out.println("函数返回类型：" + methods[i].getReturnType());
            System.out.println("函数访问修饰符：" + Modifier.toString(methods[i].getModifiers()));
            System.out.println("函数代码写法：" + methods[i]);
            System.out.println("===============================================");
        }

        //取得父类名称
        Class<?> supreClass = class1.getSuperclass();
        System.out.println("Demo6: ReflectStudent类的父类名： " + supreClass.getName());
        System.out.println("===============================================");

        //取得类实现的接口,因为接口类也属于Class,所以得到接口中的方法也是一样的方法得到哈
        Class<?> interfaces[] = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名：" + interfaces[i].getName());
        }
    }

    /**
     * Demo7: 通过Java反射机制调用类方法
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    private static void Demo7() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> class1 = null;
        class1 = Class.forName("servicecomb.demo.bean.ReflectStudent");
        System.out.println("Demo7: \n调用无参方法modifyName(): ");
        Method method = class1.getDeclaredMethod("modifyName");
        method.setAccessible(true);
        method.invoke(class1.newInstance());

        System.out.println("Demo7: \n调用有参方法setName(): ");
        method = class1.getDeclaredMethod("setName", String.class);
        ReflectStudent reflectStudent = (ReflectStudent) class1.newInstance();
        method.invoke(reflectStudent, "Demo7's xiaoming");
        System.out.println(reflectStudent.getName());
    }

    /**
     * Demo8: 通过Java反射机制得到类加载器信息
     * 在java中有三种类类加载器。[这段资料网上截取]
      		1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
      		2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
      		3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。

     * @throws ClassNotFoundException
     */
    private static void Demo8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("servicecomb.demo.bean.ReflectStudent");
        String nameString = class1.getClassLoader().getClass().getName();
        System.out.println("Demo8: 类加载器类名：" + nameString);
    }
}
