package servicecomb.springmvcserverc.java.training.list;

import com.google.inject.internal.cglib.core.$DuplicatesPredicate;
import servicecomb.demo.bean.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        Person person1 = new Person("a", 20, true, 170);
        Person person2 = new Person("b", 21, false, 175);
        Person person3 = new Person("c", 22, true, 180);
        Person person4 = new Person("d", 23, false, 185);
        Person person5 = new Person("e", 24, true, 165);
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);
        list.add(person5);
        //传统遍历list方式：iterator
        Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.isMan()) {
                System.out.println(person.toString());
            }
        }
        System.out.println();
        //在java8中，可以采用聚合操作
        //首先，通过stream方法创建Stream，然后再通过filter方法对源数据进行过滤，最后通过foeEach方法进行迭代。
        // 在聚合操作中，与Labda表达式一起使用，显得代码更加的简洁
        list.stream().filter(Person::isMan) //通过::访问类的对象方法，原本写成person -> person.isMan()
                .forEach(person -> System.out.println(person.toString()));

        //Stream生成方法

        //一、静态工厂方法
        //1、of方法，其生成的Stream是有限长度的，Stream的长度为其内的元素个数
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<String> stringStream = Stream.of("a");
        //2、generator方法，返回一个无限长度的Stream，其元素由Supplier接口提供。
        //无限长度的Stream会与filter、limit等配合使用，否则Stream会无限制的执行下去
        //以下三种形式达到的效果是一样的，只不过是下面的两个采用了Lambda表达式，简化了代码，其实际效果就是返回一个随机值
        Stream<Double> generateA = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        Stream<Double> generateB = Stream.generate(() -> Math.random());
        Stream<Double> generateC = Stream.generate(Math::random);
        //3、iterate方法，其返回的也是一个无限长度的Stream，
        //与generate方法不同的是，其是通过函数f迭代对给指定的元素种子而产生无限连续有序Stream，其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
        Stream.iterate(1, item -> item + 1)
                .limit(10)
                .forEach(System.out::println);
    }
}
