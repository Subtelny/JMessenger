package pl.janda.jmessenger.domain.model.room;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.domain.model.EventSourcedEntity;
import pl.janda.jmessenger.domain.model.user.UserId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class Message extends EventSourcedEntity {

    private MessageId messageId;

    private RoomId roomId;

    private UserId user;

    private String text;

    private LocalDateTime createdDate;

    public Message(List<DomainEvent> eventStream, int streamVersion) {
        super(eventStream, streamVersion);
    }

    public Message(MessageId messageId, RoomId roomId, UserId user, String text) {
        this.messageId = messageId;
        this.roomId = roomId;
        this.user = user;
        this.text = text;
        this.createdDate = LocalDateTime.now();

        applyEvent(new MessagedToRoom(messageId, roomId, user, text));
    }

    public void changeText(String newText) {
        Validate.notNull(newText, "Text cannot be null");
        Validate.isTrue(!text.equals(newText), "Text are equals");

        applyEvent(new MessageTextChanged(messageId, newText));
    }

    private void apply(MessagedToRoom event) {
        this.messageId = event.getMessageId();
        this.roomId = event.getRoomId();
        this.user = event.getUserId();
        this.text = event.getText();
        this.createdDate = event.getCreatedDate();
    }

    private void apply(MessageTextChanged event) {
        this.text = event.getText();
    }
}
