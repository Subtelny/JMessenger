package pl.janda.jmessenger.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.janda.jmessenger.domain.model.EntityId;


@Getter
@Setter
@AllArgsConstructor
public class EventStreamId {

    private EntityId entityId;
    private int streamVersion;

}
