package pl.janda.jmessenger.application.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.application.readmodel.MessageProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryHandler;
import pl.janda.jmessenger.infrastructure.persistence.repository.MessageProjectionRepository;
import pl.janda.jmessenger.interfaces.queries.GetAllMessagesQuery;
import pl.janda.jmessenger.interfaces.queries.GetMessageQuery;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class GetMessageHandler implements QueryHandler<MessageProjection, GetMessageQuery> {

    private final MessageProjectionRepository repository;

    @Override
    public MessageProjection handle(GetMessageQuery query) {
        return repository.findById(query.getMessageId())
                .orElseThrow(() -> new NullPointerException("Message not found"));
    }
}
