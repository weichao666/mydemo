package servicecomb.demo.common;

import servicecomb.demo.bean.Person;

public interface Hello {
  String sayHi(String name);
  String sayHello(Person person);
}
