package pl.janda.jmessenger.interfaces.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.janda.jmessenger.application.readmodel.MessageProjection;
import pl.janda.jmessenger.application.readmodel.UserProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class GetAllMessagesQuery implements Query<Collection<MessageProjection>> {

    private final LocalDateTime afterTime;

}