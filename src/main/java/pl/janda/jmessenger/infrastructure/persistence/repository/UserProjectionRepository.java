package pl.janda.jmessenger.infrastructure.persistence.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import pl.janda.jmessenger.application.readmodel.UserProjection;

public interface UserProjectionRepository extends CrudRepository<UserProjection, String> {


}
