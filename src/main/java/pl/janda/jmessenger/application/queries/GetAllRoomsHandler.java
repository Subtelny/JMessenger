package pl.janda.jmessenger.application.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.application.readmodel.RoomProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryHandler;
import pl.janda.jmessenger.infrastructure.persistence.repository.RoomProjectionRepository;
import pl.janda.jmessenger.interfaces.queries.GetAllRoomsQuery;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class GetAllRoomsHandler implements QueryHandler<Collection<RoomProjection>, GetAllRoomsQuery> {

    private final RoomProjectionRepository repository;

    @Override
    public Collection<RoomProjection> handle(GetAllRoomsQuery query) {
        return repository.findAll();
    }
}
