package servicecomb.springmvcserverc.java.training.designpattern.adapter.dianyashipeiqi;

import java.util.LinkedList;
import java.util.List;

/**
 * 电压适配器
 * 我们国家的民用电是220V，日本是110V，而我们的手机充电一般需要5V，
 * 这时候要充电，就需要一个电压适配器，将220V或者100V的输入电压变换成5V输出
 */
//测试，准备中国变压适配器和日本变压适配器各一个，定义一个方法可以根据电压找到合适的变压器，然后进行充电
public class Test {
    private List<DC5Adapter> adapters = new LinkedList<>();
    public Test() {
        this.adapters.add(new ChinaPowerAdapter());
        this.adapters.add(new JapanPowerAdapter());
    }
    public static void main(String[] args) {
        Test test = new Test();
        AC chinaAC = new AC220();
        DC5Adapter dc5Adapter = test.getPowerAdapter(chinaAC);
        dc5Adapter.outputDC5V(chinaAC);

        //去日本旅游，电压是110V
        AC japanAC = new AC110();
        DC5Adapter dc5Adapter1 = test.getPowerAdapter(japanAC);
        dc5Adapter1.outputDC5V(japanAC);
    }

    //根据电压找合适的变压器
    public DC5Adapter getPowerAdapter(AC ac) {
        DC5Adapter adapter = null;
        for (DC5Adapter dc5Adapter : this.adapters) {
            if (dc5Adapter.support(ac)) {
                adapter = dc5Adapter;
                break;
            }
        }
        if (adapter == null) {
            throw new IllegalArgumentException("没有找到合适的变压适配器");
        }
        return adapter;
    }
}
