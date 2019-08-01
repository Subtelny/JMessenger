package pl.janda.jmessenger.domain.model.user;

import lombok.*;
import org.apache.commons.lang3.Validate;
import pl.janda.jmessenger.domain.model.DomainEvent;
import pl.janda.jmessenger.domain.model.EventSourcedEntity;
import pl.janda.jmessenger.infrastructure.converter.LocalDateTimeAttributeConverter;

import javax.persistence.Convert;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class User extends EventSourcedEntity {

    private UserId userId;

    private String email;

    private String nick;

    private String password;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdDate = LocalDateTime.now();

    public User(List<DomainEvent> eventStream, int streamVersion) {
        super(eventStream, streamVersion);
    }

    public User(UserId userId, String email, String nick, String password) {
        this.userId = userId;
        this.email = email;
        this.nick = nick;
        this.password = password;

        applyEvent(new UserCreated(userId, email, nick, password));
    }

    public void changePassword(String newPassword) {
        Validate.notNull(newPassword, "Password cannot be null");
        Validate.isTrue(newPassword.length() > 5, "Password not have at least 5 characters");
        this.password = newPassword;

        applyEvent(new UserPasswordChanged(userId, newPassword));
    }

    public void changeNick(String newNick) {
        Validate.notNull(newNick, "Nick cannot be null");
        this.nick = newNick;

        applyEvent(new UserNickChanged(userId, newNick));
    }

    protected void apply(UserCreated event) {
        setUserId(event.getUserId());
        setEmail(event.getEmail());
        setNick(event.getNick());
        setPassword(event.getPassword());
    }

    protected void apply(UserNickChanged event) {
        setNick(event.getNick());
    }

    protected void apply(UserPasswordChanged event) {
        setPassword(event.getPassword());
    }

}
