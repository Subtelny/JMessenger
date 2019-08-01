package pl.janda.jmessenger.domain.model.room;

import lombok.Getter;
import pl.janda.jmessenger.domain.model.DomainEvent;

import java.time.LocalDateTime;

@Getter
public class RoomCreated implements DomainEvent {

    private RoomId id;
    private String description;

    private LocalDateTime createdDate;
    private int version;

    RoomCreated(RoomId id, String description) {
        this.id = id;
        this.description = description;

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
