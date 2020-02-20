package servicecomb.springmvcserverc.java.training.designpattern.adapter.dianyashipeiqi;

/**
 * 适配器接口，
 * 其中support()方法用于检查输入的电压是否与适配器匹配，
 * outputDC5V()方法则用于将输入的电压变换成5V后输出
 */
public interface DC5Adapter {
    boolean support(AC ac);
    int outputDC5V(AC ac);
}
