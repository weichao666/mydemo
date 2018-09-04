package servicecomb.demo.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class ValidatorPerson {
    @NotNull
    private String name;

    @Max(20)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
