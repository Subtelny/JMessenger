package pl.janda.jmessenger.domain.model.room;

import lombok.AllArgsConstructor;
import pl.janda.jmessenger.domain.model.EntityId;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Embeddable
public class MessageId extends EntityId implements Serializable{

    public MessageId(String id) {
        super(id);
    }

    @Override
    protected int hashValue() {
        return 48;
    }
}
