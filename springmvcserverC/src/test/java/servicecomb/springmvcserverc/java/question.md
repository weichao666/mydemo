# 目录


[TOC]
=================

* [目录](#%E7%9B%AE%E5%BD%95)
  * [question](#question)
  * [1、JDK和JRE有什么区别？](#1jdk%E5%92%8Cjre%E6%9C%89%E4%BB%80%E4%B9%88%E5%8C%BA%E5%88%AB)
  * [2、==和equals的区别是什么？](#2%E5%92%8Cequals%E7%9A%84%E5%8C%BA%E5%88%AB%E6%98%AF%E4%BB%80%E4%B9%88)
    * [==解读：](#%E8%A7%A3%E8%AF%BB)
    * [equals解读：](#equals%E8%A7%A3%E8%AF%BB)

* [目录](#2、==和equals的区别是什么？)
## question

## 1、JDK和JRE有什么区别？
JDK：Java Development Kit的简称，java开发工具包，提供了java的开发环境和运行环境；
JRE：Java Runtime Environment的简称，java运行环境，为java的运行提供了所需环境；
JDK其实包含了JRE，同时还包含了编译java源码的编译器javac，还包含了很多java程序调试和分析的工具；
简单来说：如果你要运行java程序，只需安装JRE就行了，如果你需要编写java程序，需要安装JDK；

## 2、==和equals的区别是什么？
### ==解读：
对于基本类型和引用类型，==的作用效果是不同的，如下所示：
* **基本类型：比较的是值是否相同；**
* **引用类型：比较的是引用是否相同；**
代码示例：
```java
String x = "string";
String y = "string";
String z = new String("string");
System.out.println(x==y); //true
System.out.println(x==z); //false
System.out.println(x.equals(y)); //true
System.out.println(x.equals(z)); //true
```
代码解读：因为x和y指向的是同一个引用，所以==也是true，而new String()方法则重写开辟了内存空间，所以==结果为false，而equals比较的一直是值，所以结果都为true；
### equals解读：
equals本质上就是==，只不过String和Integer等重写了equals方法，把它变成了值比较；
equals源码：
```java
public boolean equals(Object obj) {
  return (this == obj);
}
```
首先来看默认情况下equals比较一个有相同值的对象，代码如下：
```java
class Cat {
  public Cat(String name) {
    this.name = name;
  }
  private String name;
  public String getName() { return name;}
  public void setName(String name) {this.name = name;}
}

Cat c1 = new Cat("miaomi");
Cat c2 = new Cat("miaomi");
System.out.println(c1.equals(c2)); //false
```
输出结果是false;
即：比较普通对象时，equals比较的是引用，所以不同，而比较String和Integer等类型时，因为重写了equals方法，变成了值比较，也就相同了；

总结：==对于基本类型来说是值比较，对于引用类型来说比较的是引用；
      equals默认情况下是引用比较，只是很多类重写了equals方法，比如String、Integer等把它变成了值比较，所以一般情况下equals比较的是值是否相等；
