package pl.janda.jmessenger.domain.model.room;

import lombok.Getter;
import pl.janda.jmessenger.domain.model.DomainEvent;

import java.time.LocalDateTime;

@Getter
public class MessageTextChanged implements DomainEvent {

    private MessageId id;
    private String text;

    private LocalDateTime createdDate;
    private int version;

    MessageTextChanged(MessageId id, String text) {
        this.id = id;
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
