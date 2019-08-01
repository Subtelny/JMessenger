package pl.janda.jmessenger.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.domain.model.room.Room;
import pl.janda.jmessenger.domain.model.room.RoomId;
import pl.janda.jmessenger.domain.model.room.Rooms;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.event.EventStream;
import pl.janda.jmessenger.infrastructure.event.EventStreamId;
import pl.janda.jmessenger.infrastructure.persistence.eventstore.EventStore;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventStoreRoomRepository implements Rooms {

    private final EventStore eventStore;

    @Override
    public Room withId(RoomId roomId) {
        EventStreamId streamId = new EventStreamId(roomId, 1);
        EventStream stream = eventStore.eventStreamFor(streamId);

        return new Room(stream.getEvents(), stream.getVersion());
    }

    @Override
    public void save(Room room) {
        EventStreamId streamId = new EventStreamId(room.getRoomId(), room.getVersion());
        eventStore.append(streamId, room.getNewEvents());
    }

}