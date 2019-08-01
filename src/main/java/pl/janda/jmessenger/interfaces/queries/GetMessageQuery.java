package pl.janda.jmessenger.interfaces.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.janda.jmessenger.application.readmodel.MessageProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;

@RequiredArgsConstructor
@Getter
public class GetMessageQuery implements Query<MessageProjection> {

    private final String messageId;

}