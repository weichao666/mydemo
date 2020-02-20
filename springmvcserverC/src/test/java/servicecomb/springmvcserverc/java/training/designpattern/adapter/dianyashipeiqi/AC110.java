package servicecomb.springmvcserverc.java.training.designpattern.adapter.dianyashipeiqi;

/**
 * 输出110V交流电类
 */
public class AC110 implements AC {
    public final int output = 110;
    @Override
    public int outputAC() {
        return output;
    }
}
