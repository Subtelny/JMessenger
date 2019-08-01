package pl.janda.jmessenger.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.event.EventStream;
import pl.janda.jmessenger.infrastructure.event.EventStreamId;
import pl.janda.jmessenger.infrastructure.persistence.eventstore.EventStore;

@Component
@RequiredArgsConstructor
public class EventStoreUserRepository implements Users {

    private final EventStore eventStore;

    @Override
    public User withId(UserId userId) {
        EventStreamId streamId = new EventStreamId(userId, 1);
        EventStream stream = eventStore.eventStreamFor(streamId);

        return new User(stream.getEvents(), stream.getVersion());
    }
    @Override
    public void save(User user) {
        EventStreamId streamId = new EventStreamId(user.getUserId(), user.getVersion());
        eventStore.append(streamId, user.getNewEvents());
    }
}