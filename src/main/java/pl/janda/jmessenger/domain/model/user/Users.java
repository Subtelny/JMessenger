package pl.janda.jmessenger.domain.model.user;

public interface Users {

    User withId(UserId userId);

    void save(User user);

}
