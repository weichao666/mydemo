package servicecomb.springmvcserverc.java.training.designpattern.publishSubscribe;

public class Event {
    private String message;

    public Event(String message) {
        this.message = message;
        System.out.println("event message: " + message);
    }
    public String getMessage() {
        return message;
    }
}
