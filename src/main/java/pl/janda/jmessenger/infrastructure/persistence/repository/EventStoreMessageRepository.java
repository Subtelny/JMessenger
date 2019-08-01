package pl.janda.jmessenger.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.domain.model.room.*;
import pl.janda.jmessenger.infrastructure.event.EventStream;
import pl.janda.jmessenger.infrastructure.event.EventStreamId;
import pl.janda.jmessenger.infrastructure.persistence.eventstore.EventStore;

@Component
@RequiredArgsConstructor
public class EventStoreMessageRepository implements Messages {

    private final EventStore eventStore;

    @Override
    public Message withId(MessageId messageId) {
        EventStreamId streamId = new EventStreamId(messageId, 1);
        EventStream stream = eventStore.eventStreamFor(streamId);

        return new Message(stream.getEvents(), stream.getVersion());
    }

    @Override
    public void save(Message message) {
        EventStreamId streamId = new EventStreamId(message.getMessageId(), message.getVersion());
        eventStore.append(streamId, message.getNewEvents());
    }
}