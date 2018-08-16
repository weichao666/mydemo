package servicecomb.springmvcserverc.eventbus;

import com.google.common.eventbus.EventBus;
import org.apache.servicecomb.foundation.common.event.EventManager;
import org.junit.Test;

public class TestEventBus {
    @Test
    public void testReceiveEvent() {
        EventListener listener = new EventListener();
        //test com.google.common.eventbus.EventBus
        EventBus eventBus = new EventBus("test");
        eventBus.register(listener);
        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.unregister(listener);
        eventBus.post(new TestEvent(400));
        System.out.println("LastMessage: " + listener.getLastMessage());
        //test org.apache.servicecomb.foundation.common.event.EventManager
        EventManager.register(listener);
        EventManager.post(new TestEvent(500));
        EventManager.post(new TestEvent(600));
        EventManager.unregister(listener);
        EventManager.post(new TestEvent(700));
        System.out.println("LastMessage: " + listener.getLastMessage());

    }
}
