package pl.janda.jmessenger.infrastructure.persistence.eventstore;

import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.infrastructure.event.EventStream;
import pl.janda.jmessenger.infrastructure.event.EventStreamId;
import pl.janda.jmessenger.infrastructure.event.StoredDomainEvent;

import java.util.List;


public interface EventStore {

    void append(EventStreamId eventStreamId, List<DomainEvent> newEvents);

    List<StoredDomainEvent> eventsSince(long eventId);

    EventStream eventStreamFor(EventStreamId eventStreamId);

}
