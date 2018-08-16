package servicecomb.springmvcserverc.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadEventListener {
    boolean notDelivered = false;
    //如果EventBus发送的消息都不是订阅者关心的称之为Dead Event
    @Subscribe
    public void listen(DeadEvent event) {
        notDelivered = true;
    }

    public boolean isNotDelivered() {
        return notDelivered;
    }
    public boolean setNotDelivered() {
        this.notDelivered = false;
        return this.notDelivered;
    }
}
