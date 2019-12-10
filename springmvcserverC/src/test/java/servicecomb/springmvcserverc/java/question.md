# 目录
[toc]
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
###
