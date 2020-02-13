package servicecomb.springmvcserverc.java.training.designpattern.publishSubscribe;

import com.google.common.eventbus.Subscribe;

public class EventListener {
    private String message;

    @Subscribe
    public void getMessage(Event event) {
        System.out.println("get Message: " + event.getMessage());
    }
}
