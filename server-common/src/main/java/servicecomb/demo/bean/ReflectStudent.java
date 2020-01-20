package servicecomb.demo.bean;

import java.io.Serializable;

public class ReflectStudent implements Serializable {
    private String name;
    private int age;
    public ReflectStudent() {

    }
    public ReflectStudent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void modifyName() {
        name = "modified";
        System.out.println("name changed to : " + name);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReflectStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
