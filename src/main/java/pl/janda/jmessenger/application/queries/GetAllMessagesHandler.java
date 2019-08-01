package pl.janda.jmessenger.application.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.application.readmodel.MessageProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryHandler;
import pl.janda.jmessenger.infrastructure.persistence.repository.MessageProjectionRepository;
import pl.janda.jmessenger.interfaces.queries.GetAllMessagesQuery;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class GetAllMessagesHandler implements QueryHandler<Collection<MessageProjection>, GetAllMessagesQuery> {

    private final MessageProjectionRepository repository;

    @Override
    public Collection<MessageProjection> handle(GetAllMessagesQuery query) {
        return repository.findAllByCreatedDateAfter(query.getAfterTime());
    }
}
