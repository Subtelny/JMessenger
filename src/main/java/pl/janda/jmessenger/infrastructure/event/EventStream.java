package pl.janda.jmessenger.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.domain.model.EntityId;

import java.util.List;


@Getter
@AllArgsConstructor
public class EventStream {

    private List<DomainEvent> events;
    private int version;

}
