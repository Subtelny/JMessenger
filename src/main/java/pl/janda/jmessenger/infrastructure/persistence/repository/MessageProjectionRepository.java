package pl.janda.jmessenger.infrastructure.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import pl.janda.jmessenger.application.readmodel.MessageProjection;
import pl.janda.jmessenger.application.readmodel.UserProjection;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MessageProjectionRepository extends CrudRepository<MessageProjection, String> {

    Collection<MessageProjection> findAllByCreatedDateAfter(LocalDateTime time);

}
