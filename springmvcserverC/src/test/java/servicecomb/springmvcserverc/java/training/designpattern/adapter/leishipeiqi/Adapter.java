package servicecomb.springmvcserverc.java.training.designpattern.adapter.leishipeiqi;

public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.adapteeRequest();
    }
}
