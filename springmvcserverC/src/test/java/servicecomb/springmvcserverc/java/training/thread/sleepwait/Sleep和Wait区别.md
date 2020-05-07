## Sleep和Wait的区别有四个：

* **1、sleep是Thread中的方法，但是wait是Object中的方法。**

* **2、sleep方法不会释放lock，但是wait会释放，而且会加入到等待队列中。**

* **3、sleep方法不依赖于同步器synchronized，但是wait需要依赖synchronized关键字。**

* **4、sleep不需要被唤醒（休眠之后退出阻塞），但是wait需要（不指定时间需要被别人中断）**
