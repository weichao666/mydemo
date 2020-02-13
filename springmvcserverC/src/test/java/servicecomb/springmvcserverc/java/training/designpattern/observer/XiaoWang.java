package servicecomb.springmvcserverc.java.training.designpattern.observer;

public class XiaoWang implements Person {
    private String name = "xiaowang";

    @Override
    public void getMessage(String s) {
        System.out.println(name + "接到了小美打过来的电话，电话内容是：" + s);
    }
}
