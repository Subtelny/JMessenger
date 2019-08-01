package pl.janda.jmessenger.infrastructure.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import pl.janda.jmessenger.application.readmodel.MessageProjection;
import pl.janda.jmessenger.application.readmodel.RoomProjection;

import java.util.Collection;

public interface RoomProjectionRepository extends CrudRepository<RoomProjection, String> {

    Collection<RoomProjection> findAll();

}
