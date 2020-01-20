package servicecomb.demo.bean;

import lombok.Getter;
import lombok.Setter;

public class Person {
  private String name;

  private int Age;
  
  private boolean man;

  private Integer eValid;

  public Person() {
  }

  public Person(String name, int age, boolean man, Integer eValid) {
    this.name = name;
    Age = age;
    this.man = man;
    this.eValid = eValid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isMan() {
    return man;
  }

  public void setMan(boolean man) {
    this.man = man;
  }

  public String toString() {
    return "name=" + name + ";age=" + Age + ";man=" + man + ";eValid=" + eValid;
  }

  public int getAge() {
    return Age;
  }

  public void setAge(int age) {
    Age = age;
  }

  public Integer geteValid() {
    return eValid;
  }

  public void seteValid(Integer eValid) {
    this.eValid = eValid;
  }
}
