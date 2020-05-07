package servicecomb.springmvcserverc.java.training.classtest.classforname;

//java反射机制中class.forName和classloader的区别
//class.forName()除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。当然还可以指定是否执行静态块。
//classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容，只有在newInstance才会去执行static块。

//class.forName加载类是将类进行了初始化，而ClassLoader的loadClass并没有对类进行初始化，只是把类加载到了虚拟机中。
//应用场景
//在我们熟悉的Spring框架中的IOC实现就是使用的ClassLoader。
//而在我们使用JDBC时通常是使用Class.forName()方法来加载数据库连接驱动。
// 这是因为在JDBC规范中明确要求Driver（数据库驱动）类必须向DriverManager注册自己。
//以MySql的驱动为例解释：
//public class Driver extends NonRegisteringDriver implements java.sql.Driver {
//    // ~ Static fields/initializers
//    // ---------------------------------------------
//
//    //
//    // Register ourselves with the DriverManager
//    //
//    static {
//        try {
//            java.sql.DriverManager.registerDriver(new Driver());
//        } catch (SQLException E) {
//            throw new RuntimeException("Can't register driver!");
//        }
//    }
//
//    // ~ Constructors
//    // -----------------------------------------------------------
//
//    /**
//     * Construct a new driver and register it with DriverManager
//     *
//     * @throws SQLException
//     *             if a database error occurs.
//     */
//    public Driver() throws SQLException {
//        // Required for Class.forName().newInstance()
//    }
//}
//我们看到Driver注册到DriverManager中的操作写在了静态代码块中，这就是为什么在写JDBC时使用Class.forName()的原因了。
public class FDDTest {
    public static void main(String[] args) {
        String user = "servicecomb.springmvcserverc.java.training.classtest.classforname.User";
        test(user);
    }

    public static void test(String user) {
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            System.out.println("classloader testing...");
            Class<?> loaderUser = loader.loadClass(user);
            System.out.println("user " + loaderUser.getName());
            System.out.println("-----------------------------");
            Class forNameUser = Class.forName(user);
            System.out.println("Class.forName testing...");
            System.out.println("user " + forNameUser.getName());
            System.out.println("-----------------------------");
            System.out.println("Class.forName.newInstance() testing...");
            Class.forName(user).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
