package pl.janda.jmessenger.application.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.application.readmodel.RoomProjection;
import pl.janda.jmessenger.application.readmodel.UserProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryHandler;
import pl.janda.jmessenger.infrastructure.persistence.repository.RoomProjectionRepository;
import pl.janda.jmessenger.infrastructure.persistence.repository.UserProjectionRepository;
import pl.janda.jmessenger.interfaces.queries.GetAllRoomsQuery;
import pl.janda.jmessenger.interfaces.queries.GetUserQuery;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class GetUserHandler implements QueryHandler<UserProjection, GetUserQuery> {

    private final UserProjectionRepository repository;

    @Override
    public UserProjection handle(GetUserQuery query) {
        return repository.findById(query.getUserId())
                .orElseThrow(() -> new NullPointerException("User not found"));
    }
}
