package pl.janda.jmessenger.infrastructure.event;

import lombok.Getter;
import org.hibernate.annotations.Type;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.infrastructure.serializer.EventSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity()
@Getter
public class StoredEvent implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String aggregateId;

    private String aggregateType;

    @Lob
    private String payLoad;

    @Column(nullable = false)
    private int version;

    protected StoredEvent() {

    }

    public StoredEvent(String aggregateId, String aggregateType, String payLoad, int version) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.payLoad = payLoad;
        this.version = version;
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> T toDomainEvent() {
        Class<T> domainEventClass = null;

        try {
            domainEventClass = (Class<T>) Class.forName(aggregateType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return EventSerializer.instance().deserialize(payLoad, domainEventClass);
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setAggregateType(String aggregateType) {

        this.aggregateType = aggregateType;
    }

    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }

}
