package servicecomb.springmvcserverc.java.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("out/test.txt")));
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
      ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("out/test.txt")));
      user = (User) objectInputStream.readObject();
      System.out.println("反序列化成功");
      System.out.println("用户编号: " + user.getUserId() + "\n用户姓名: " + user.getUserName() + "\n用户年龄: " + user.getAge());
    } catch (Exception e) {
      System.out.println("readUser: " + e.getMessage());
    }
    return user;
  }
}
