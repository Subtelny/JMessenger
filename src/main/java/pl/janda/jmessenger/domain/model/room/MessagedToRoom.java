package pl.janda.jmessenger.domain.model.room;

import lombok.Getter;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.domain.model.user.UserId;

import java.time.LocalDateTime;

@Getter
public class MessagedToRoom implements DomainEvent {

    private MessageId messageId;
    private RoomId roomId;
    private UserId userId;

    private String text;

    private LocalDateTime createdDate;
    private int version;

    MessagedToRoom(MessageId messageId, RoomId roomId, UserId userId, String text) {
        this.messageId = messageId;
        this.roomId = roomId;
        this.userId = userId;
        this.text = text;

        createdDate = LocalDateTime.now();
        version = 1;
    }

    @Override
    public LocalDateTime createdDate() {
        return createdDate;
    }

    @Override
    public int version() {
        return version;
    }
}
