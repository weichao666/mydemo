package servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory;

public class MiPhone implements Phone {
    public MiPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make xiaomi phone");
    }
}
