package servicecomb.springmvcserverc.java.serializable;

import java.io.Serializable;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private String userId;
  private String userName;
  //Transient 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，
  //在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null
  private transient int age;

  public User() {}

  public User(String userId, String userName, int age) {
    this.userId = userId;
    this.userName = userName;
    this.age = age;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User{" +
            "userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            ", age=" + age +
            '}';
  }
}
