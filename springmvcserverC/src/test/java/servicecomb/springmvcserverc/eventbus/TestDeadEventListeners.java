package servicecomb.springmvcserverc.eventbus;

import com.google.common.eventbus.EventBus;
import org.apache.servicecomb.foundation.common.event.EventManager;
import org.junit.Test;

public class TestDeadEventListeners {
    @Test
    public void testDeadEventListeners() {
        DeadEventListener deadEventListener = new DeadEventListener();
        EventBus eventBus = new EventBus("test");
        eventBus.register(deadEventListener);
        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        System.out.println("deadEvent: " + deadEventListener.isNotDelivered());

        boolean notDelivered = deadEventListener.setNotDelivered();
        System.out.println("set notDelivered to: " + notDelivered);
        EventManager.register(deadEventListener);
        EventManager.post(new TestEvent(400));
        System.out.println("deadEvent: " + deadEventListener.isNotDelivered());
    }
}
