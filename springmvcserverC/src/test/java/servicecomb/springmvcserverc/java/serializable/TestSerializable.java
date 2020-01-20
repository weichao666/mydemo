package servicecomb.springmvcserverc.java.serializable;

import java.io.*;

import org.junit.Test;

public class TestSerializable {

  @Test
  public void test() {
    writeUser();
    readUser();
  }

  public static void writeUser() {
    try {
      User user = new User();
      user.setUserId("1");
      user.setUserName("aaa");
      user.setAge(18);
      //用File打开本地文件，也可直接在new FileOutputStream的构造方法里写文件路径，构造方法里会去调用new File
      // 实例化FileOutputStream，打开输出流
      //ObjectOutputStream——用来向文件中写入对象
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\code\\mydemo\\springmvcserverC\\src\\test\\java\\servicecomb\\springmvcserverc\\java\\serializable\\out\\test.txt"));
      objectOutputStream.writeObject(user);
      System.out.println("序列化成功");
      objectOutputStream.close();
    } catch (Exception e) {
      System.out.println("writeUser: " + e.getMessage());
    }
  }

  private static User readUser() {
    User user = null;
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\code\\mydemo\\springmvcserverC\\src\\test\\java\\servicecomb\\springmvcserverc\\java\\serializable\\out\\test.txt"));
      user = (User) objectInputStream.readObject();
      System.out.println("反序列化成功");
      System.out.println("用户编号: " + user.getUserId() + "\n用户姓名: " + user.getUserName() + "\n添加了transient关键字反序列化：用户年龄: " + user.getAge());
    } catch (Exception e) {
      System.out.println("readUser: " + e.getMessage());
    }
    return user;
  }
}
