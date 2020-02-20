package servicecomb.springmvcserverc.java.training.designpattern.adapter.dianyashipeiqi;

/**
 * 输出220V交流电类
 */
public class AC220 implements AC {
    public final int output = 220;
    @Override
    public int outputAC() {
        return output;
    }
}
