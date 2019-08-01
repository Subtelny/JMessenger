package pl.janda.jmessenger.infrastructure.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.infrastructure.persistence.eventstore.EventStore;
import pl.janda.jmessenger.infrastructure.persistence.hibernate.LastStoredEventHibernate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventNotifier implements EventNotifiable {

    private final ApplicationEventPublisher publisher;
    private final LastStoredEventHibernate lastStoredEventHibernate;
    private EventStore eventStore;

    @Override
    @Transactional
    public void notifyEvents() {
        LastStoredEvent lastStoredEvent = lastStoredEventHibernate.findById(1L).orElse(new LastStoredEvent());
        List<StoredDomainEvent> events = eventStore.eventsSince(lastStoredEvent.getLastEvent());

        Optional<Long> lastEventId = events.stream().map(StoredDomainEvent::getEventId).max(Long::compareTo);
        if(lastEventId.isPresent()) {
            lastStoredEvent.setLastEvent(lastEventId.get());
            lastStoredEventHibernate.save(lastStoredEvent);
        }

        events.forEach(i -> publisher.publishEvent(i.getDomainEvent()));
    }

    public void setEventStore(EventStore eventStore) {
        this.eventStore = eventStore;
    }
}
