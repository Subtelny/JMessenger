package pl.janda.jmessenger.infrastructure.event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LastStoredEvent {

    @Id
    private Long id = 1L;

    @Getter
    @Setter
    private long lastEvent;

}
