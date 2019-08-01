package pl.janda.jmessenger.domain.model;

import java.time.LocalDateTime;

public interface DomainEvent {

    LocalDateTime createdDate();
    int version();

}
