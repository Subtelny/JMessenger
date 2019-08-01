package pl.janda.jmessenger.infrastructure.persistence.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.infrastructure.event.*;
import pl.janda.jmessenger.infrastructure.persistence.eventstore.EventStore;
import pl.janda.jmessenger.infrastructure.serializer.EventSerializer;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class EventStoreHibernate implements EventStore {

    private final EntityManagerFactory entityManagerFactory;
    private final EventNotifiable eventNotifier;

    @PostConstruct
    public void init() {
        eventNotifier.setEventStore(this);
    }

    @Override
    public void append(EventStreamId eventStreamId, List<DomainEvent> newEvents) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        int i = 0;
        for (DomainEvent newEvent : newEvents) {
            appendEvent(entityManager, eventStreamId, newEvent, i);
            i++;
        }

        entityManager.getTransaction().commit();

        eventNotifier.notifyEvents();
    }

    @Override
    public List<StoredDomainEvent> eventsSince(long lastEventId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoredEvent> query = builder.createQuery(StoredEvent.class);

        Root<StoredEvent> root = query.from(StoredEvent.class);
        Predicate eventIdGreaterThan = builder.greaterThan(root.get("id"), lastEventId);
        query.where(eventIdGreaterThan);
        query.orderBy(builder.asc(root.get("id")));

        List<StoredEvent> result = entityManager.createQuery(query).getResultList();

        try {
            List<StoredDomainEvent> list = new ArrayList<>();
            for (StoredEvent storedEvent : result) {
                DomainEvent event = buildDomainEvent(storedEvent);

                StoredDomainEvent storedDomainEvent = new StoredDomainEvent(event, storedEvent.getId());
                list.add(storedDomainEvent);
            }
        return list;
        } catch (Exception e) {
            throw new EventStoreException("Error while creating domain event - " + e.getMessage());
        }
    }

    @Override
    public EventStream eventStreamFor(EventStreamId eventStreamId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoredEvent> query = builder.createQuery(StoredEvent.class);

        Root<StoredEvent> root = query.from(StoredEvent.class);
        Predicate aggregateIdEqual = builder.equal(root.get("aggregateId"), eventStreamId.getEntityId().getId());
        query.where(aggregateIdEqual);
        query.orderBy(builder.asc(root.get("version")));

        List<StoredEvent> result = entityManager.createQuery(query).getResultList();

        try {
            int version = 0;
            List<DomainEvent> list = new ArrayList<>();
            for (StoredEvent storedEvent : result) {
                DomainEvent event = buildDomainEvent(storedEvent);

                list.add(event);
                version = storedEvent.getVersion();
            }

            return new EventStream(list, version);
        } catch (Exception e) {
            throw new EventStoreException("Error while creating domain event - " + e.getMessage());
        }
    }

    private DomainEvent buildDomainEvent(StoredEvent storedEvent) throws ClassNotFoundException {
        Class<DomainEvent> eventClass = (Class<DomainEvent>) Class.forName(storedEvent.getAggregateType());
        DomainEvent event = EventSerializer.instance().deserialize(storedEvent.getPayLoad(), eventClass);

        return event;
    }

    private void appendEvent(EntityManager entityManager, EventStreamId eventStreamId, DomainEvent event, int index) {
        String payload = EventSerializer.instance().serialize(event);

        StoredEvent storedEvent = new StoredEvent(
                eventStreamId.getEntityId().getId(),
                event.getClass().getName(),
                payload,
                eventStreamId.getStreamVersion() + index);

        entityManager.persist(storedEvent);
    }
}
