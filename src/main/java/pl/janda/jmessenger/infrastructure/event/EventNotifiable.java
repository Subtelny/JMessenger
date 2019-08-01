package pl.janda.jmessenger.infrastructure.event;

import pl.janda.jmessenger.infrastructure.persistence.eventstore.EventStore;

public interface EventNotifiable {

    void notifyEvents();

    void setEventStore(EventStore eventStore);

}
