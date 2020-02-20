package servicecomb.springmvcserverc.java.training.designpattern.adapter.duixiangshipeiqi;

/**
 * 目标接口
 * 怎么才可以在目标接口中的 request() 调用 Adaptee 的 adapteeRequest() 方法呢？
 */
public interface Target {
    void request();
}
