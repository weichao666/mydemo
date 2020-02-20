package servicecomb.springmvcserverc.java.training.designpattern.factory.abstractfactory;

public class MAC implements PC {
    public MAC() {
        this.make();
    }
    @Override
    public void make() {
        System.out.println("make MAC");
    }
}
