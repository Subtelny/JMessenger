package pl.janda.jmessenger.domain.model.user;

import lombok.Getter;
import pl.janda.jmessenger.domain.model.DomainEvent;

import java.time.LocalDateTime;

@Getter
public class UserNickChanged implements DomainEvent {

    private UserId userId;

    private String nick;

    private LocalDateTime createdDate;
    private int version;

    UserNickChanged(UserId userId, String nick) {
        this.userId = userId;
        this.nick = nick;

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
