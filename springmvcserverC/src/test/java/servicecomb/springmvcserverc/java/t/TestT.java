package servicecomb.springmvcserverc.java.t;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestT {
  public class Fruit {};
  public class Apple extends Fruit {};

  public void print(ArrayList<? extends Fruit> list) {
    for (Fruit f : list) {
      System.out.println(f);
    }
    //使用ArrayList<? extends Fruit>，就能保证print函数在被调用时不报错，但是在print函数中只能对这个list进行遍历，不能执行添加元素的操作
//    list.add(new Apple());  //会在编译阶段就报错
  }

  @Test
  public void test1() {
    ArrayList<Apple> list = new ArrayList<Apple>();
    list.add(new Apple());
    list.add(new Apple());
    //使用ArrayList<? extends Fruit>，就能正常调用print函数不报错，传参ArrayList<Apple>，如果直接使用ArrayList<Fruit>就不行，因为ArrayList<Apple>并不是ArrayList<Fruit>的子类
    print(list);
  }

  //泛型只在编译阶段有效,在编译之后程序会采取去泛型化的措施
  //泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型
  @Test
  public void test2() {
    List<String> stringList = new ArrayList<String>();
    List<Integer> integerList = new ArrayList<Integer>();
    Class classStringArrayList = stringList.getClass();
    Class classIntegerArrayList = integerList.getClass();
    System.out.println("classStringArrayList: " + classStringArrayList);
    System.out.println("classIntegerArrayList: " + classIntegerArrayList);
    if (classStringArrayList.equals(classIntegerArrayList)) {
      System.out.println("泛型测试，类型相同。");
    }
  }
}
