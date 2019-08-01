package pl.janda.jmessenger.application.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.application.readmodel.RoomProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryHandler;
import pl.janda.jmessenger.infrastructure.persistence.repository.RoomProjectionRepository;
import pl.janda.jmessenger.interfaces.queries.GetRoomQuery;

@Component
@RequiredArgsConstructor
public class GetRoomHandler implements QueryHandler<RoomProjection, GetRoomQuery> {

    private final RoomProjectionRepository repository;

    @Override
    public RoomProjection handle(GetRoomQuery query) {
        return repository.findById(query.getRoomId())
                .orElseThrow(() -> new NullPointerException("Room not found"));
    }
}
