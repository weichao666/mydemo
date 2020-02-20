package servicecomb.springmvcserverc.java.training.designpattern.factory.abstractfactory;

public class MiPC implements PC {
    public MiPC() {
        this.make();
    }
    @Override
    public void make() {
        System.out.println("make xiaomi PC");
    }
}
