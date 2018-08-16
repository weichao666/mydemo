package servicecomb.springmvcserverc.eventbus;

import com.google.common.eventbus.EventBus;
import org.apache.servicecomb.foundation.common.event.EventManager;
import org.junit.Test;

public class TestMultipleEvents {
    @Test
    public void testMultipleEvents() {
        MultipleListener multipleListener = new MultipleListener();
        //test com.google.common.eventbus.EventBus
        EventBus eventBus = new EventBus("test");
        eventBus.register(multipleListener);
        eventBus.post(new Integer(100));
        eventBus.post(new Integer(200));
        eventBus.post(new Integer(300));
        eventBus.post(new Long(800));
        eventBus.post(new Long(800990));
        eventBus.post(new Long(800882934));
        System.out.println("LastInteger: " + multipleListener.getLastInteger());
        System.out.println("LastLong: " + multipleListener.getLastLong());
        //test org.apache.servicecomb.foundation.common.event.EventManager
        EventManager.register(multipleListener);
        EventManager.post(new Integer(400));
        EventManager.post(new Long(400000000));
        System.out.println("LastInteger: " + multipleListener.getLastInteger());
        System.out.println("LastLong: " + multipleListener.getLastLong());
    }
}
