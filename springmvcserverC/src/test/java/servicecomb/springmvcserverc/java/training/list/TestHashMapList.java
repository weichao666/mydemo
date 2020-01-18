package servicecomb.springmvcserverc.java.training.list;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import servicecomb.demo.bean.Person;

public class TestHashMapList {
  @Test
  public void testHashMap() {
    //HashMap
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put(null, null);
    map1.put("dd", "ddd");
    map1.put("bb", "bbb");
    map1.put("aa", "aaa");
    map1.put("cc", "ccc");
    map1.put("ee", "eee");
//    for (Map.Entry map:map1.entrySet()
//    ) {
//      System.out.println(map.toString());
//    }
//    System.out.println(map1);
//    System.out.println(map1.containsKey(null));
    Iterator<String> iterator = map1.keySet().iterator();
    while (iterator.hasNext()) {
      System.out.println("map.get(key) is : " + map1.get(iterator.next()));
    }
    System.out.println();

    //TreeMap
    Map<String, String> map3 = new TreeMap<>();
    map3.put("a", "aaa");
    map3.put("b", "bbb");
    map3.put("c", "ccc");
    map3.put("d", "ddd");
    System.out.println(map3);

    //HashTable
    Map<String, String> map2 = new Hashtable<>();
    map2.put("cc", "dd");
    System.out.println(((Hashtable<String, String>) map2).contains("dd"));
    System.out.println(map2.containsKey("cc"));
//    map2.put(null, null);

  }
  @Test
  public void testList() {
    List<String> list1 = new ArrayList<>();
    list1.add("a");
    list1.add("b");
    list1.add("c");
    list1.add("b");
    System.out.println();
    System.out.println(list1);
    Iterator iterator1 = list1.listIterator();
    while (iterator1.hasNext()) {
      System.out.println(iterator1.next());
    }
    //list转化成数组，并打印出数组
    System.out.println(Arrays.toString(list1.toArray()));
    String[] str = list1.toArray(new String[list1.size()]);
    //数组转化成list
    List list2 = Arrays.asList(str);
    System.out.println(list2);
    //list转String
    List<String> list3 = Arrays.asList("a", "b", "c", "d");
    List<String> list4 = Lists.newArrayList("a", "b", "c");
    //方法一
    System.out.println(String.join(",", list3));
    //方法二
    System.out.println(list3.stream().collect(Collectors.joining(",")));
    //String转换成list
    String str1 = "abcde";
    List<String> list5 = Arrays.asList(str1.split(","));
    System.out.println(list5);

    //不使用泛型的List可以放不同类型的对象
    List list6 = new ArrayList(10);
    list6.add(10);
    list6.add("aa");
    list6.add(new Person());
    System.out.println(list6);
  }

  @Test
  public void testQueue() {
    //队列是一种特殊的线性表，它只允许在表的前端进行删除操作，而在表的后端进行插入操作；
    //LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
    Queue<String> queue = new LinkedList<>();
    queue.offer("a");
    queue.offer("b");
    queue.offer("c");
    queue.offer("d");
    queue.offer("e");
    System.out.println(queue);
    System.out.println("=========");
    System.out.println("element = " + queue.element()); //返回第一个元素
    System.out.println(queue);
    System.out.println("=================");
    System.out.println("peek = " + queue.peek()); //返回第一个元素
    System.out.println(queue);
    System.out.println("======================");
    System.out.println("poll = " + queue.poll()); //返回第一个元素，并在队列中删除  //在获取元素失败的时候会返回空
    System.out.println(queue);
    System.out.println("=============");
    System.out.println("remove = " + queue.remove()); //返回第一个元素，并在队列中删除  //remove() 失败的时候会抛出异常
    System.out.println(queue);
  }

  @Test
  public void testArray() {
    //对象数组
    Person[] person = new Person[5];
    Person p1 = new Person("aa", 18, true, 18);
    Person p2 = new Person("bb", 19, false, 19);

    person[0] = p1;
    person[1] = p2;
    System.out.println(person[0].toString() + person[1]);
  }
}
