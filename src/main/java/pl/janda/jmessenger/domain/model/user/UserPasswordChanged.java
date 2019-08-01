package pl.janda.jmessenger.domain.model.user;

import lombok.Getter;
import pl.janda.jmessenger.domain.model.DomainEvent;

import java.time.LocalDateTime;

@Getter
public class UserPasswordChanged implements DomainEvent {

    private UserId userId;
    private String password;

    private LocalDateTime createdDate;
    private int version;

    UserPasswordChanged(UserId userId, String password) {
        this.userId = userId;
        this.password = password;

        createdDate = LocalDateTime.now();
        version = 1;
    }

    @Override
    public LocalDateTime createdDate() {
        return createdDate;
    }

    @Override
    public int version() {
        return version;
    }
}
