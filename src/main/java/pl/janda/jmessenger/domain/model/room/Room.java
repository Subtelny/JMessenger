package pl.janda.jmessenger.domain.model.room;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.domain.model.EventSourcedEntity;
import pl.janda.jmessenger.domain.model.user.UserId;

import java.util.List;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Room extends EventSourcedEntity {

    private RoomId roomId;

    private String description;

    public Room(List<DomainEvent> eventStream, int streamVersion) {
        super(eventStream, streamVersion);
    }

    public Room(RoomId roomId, String description) {
        this.roomId = roomId;
        this.description = description;

        applyEvent(new RoomCreated(roomId, description));
    }

    public void changeDescription(String newDescription) {
        Validate.notNull(newDescription, "Description cannot be null");
        Validate.isTrue(!description.equals(newDescription), "Descriptions are equals");

       applyEvent(new RoomDescriptionChanged(roomId, description));
    }

    public Message message(UserId user, String text) {
        return new Message(new MessageId(UUID.randomUUID().toString()), roomId, user, text);
    }

    protected void apply(RoomCreated event) {
        this.roomId = event.getId();
        this.description  = event.getDescription();
    }

    protected void apply(RoomDescriptionChanged event) {
        this.description = event.getDescription();
    }

}
