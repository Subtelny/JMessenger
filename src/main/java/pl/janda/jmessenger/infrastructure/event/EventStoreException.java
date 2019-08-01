package pl.janda.jmessenger.infrastructure.event;

public class EventStoreException extends RuntimeException {

    public EventStoreException(String message) {
        super(message);
    }

    public EventStoreException(String message, Throwable cause) {
        super(message, cause);
    }

}
