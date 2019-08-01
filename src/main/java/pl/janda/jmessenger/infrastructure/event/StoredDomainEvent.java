package pl.janda.jmessenger.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.domain.model.EntityId;


@Getter
@AllArgsConstructor
public class StoredDomainEvent {

    private final DomainEvent domainEvent;
    private final long eventId;

}
