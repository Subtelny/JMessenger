package pl.janda.jmessenger.infrastructure.persistence.hibernate;

import org.springframework.data.repository.CrudRepository;
import pl.janda.jmessenger.infrastructure.event.LastStoredEvent;

public interface LastStoredEventHibernate extends CrudRepository<LastStoredEvent, Long> {

}
