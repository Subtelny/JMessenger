package pl.janda.jmessenger.interfaces.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.janda.jmessenger.application.readmodel.UserProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;

@RequiredArgsConstructor
@Getter
public class GetUserQuery implements Query<UserProjection> {

    private final String userId;

}