package pl.janda.jmessenger.interfaces.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.janda.jmessenger.application.readmodel.RoomProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;

@RequiredArgsConstructor
@Getter
public class GetRoomQuery implements Query<RoomProjection> {

    private final String roomId;

}