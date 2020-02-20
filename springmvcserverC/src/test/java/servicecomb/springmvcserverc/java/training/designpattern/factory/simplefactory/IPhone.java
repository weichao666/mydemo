package servicecomb.springmvcserverc.java.training.designpattern.factory.simplefactory;

public class IPhone implements Phone {
    public IPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make iphone");
    }
}
