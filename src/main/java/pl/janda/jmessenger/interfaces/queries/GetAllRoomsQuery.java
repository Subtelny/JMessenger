package pl.janda.jmessenger.interfaces.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.janda.jmessenger.application.readmodel.RoomProjection;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class GetAllRoomsQuery implements Query<Collection<RoomProjection>> {

    //TODO
    //filters

}