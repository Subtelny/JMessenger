package pl.janda.jmessenger.domain.model.user;

import lombok.Getter;
import pl.janda.jmessenger.domain.model.DomainEvent;

import java.time.LocalDateTime;

@Getter
public class UserCreated implements DomainEvent {

    private UserId userId;

    private String email;

    private String nick;

    private String password;

    private int version;

    private LocalDateTime createdDate;

    public UserCreated(UserId userId, String email, String nick, String password) {
        this.userId = userId;
        this.nick = nick;
        this.email = email;
        this.password = password;

        this.version = 1;
        createdDate = LocalDateTime.now();
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