package servicecomb.springmvcserverc.java.training.clone;

import servicecomb.springmvcserverc.java.serializable.User;

public class TestClone {
    public static void main(String[] args) {
        User user1 = new User("1", "xiaoming", 20);
        User user2 = CloneUtils.clone(user1);  // 深度克隆
        user2.setUserName("zhangsan");
        User user3 = CloneUtils.clone(user1);
        user3.setAge(10);
        System.out.println("User1: " + user1);
        System.out.println("User2: " + user2);
        System.out.println("User3: " + user3);
    }
}
