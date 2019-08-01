package pl.janda.jmessenger.application.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.domain.model.user.UserCreated;
import pl.janda.jmessenger.domain.model.user.UserNickChanged;
import pl.janda.jmessenger.infrastructure.persistence.repository.UserProjectionRepository;

@Component
@RequiredArgsConstructor
public class UserProjectionEventListener {

    private final UserProjectionRepository repository;

    @EventListener
    public void handleUserCreated(UserCreated event) {
        UserProjection userProjection = new UserProjection(
                event.getUserId().getId(),
                event.getEmail(),
                event.getNick(),
                event.getPassword());

        repository.save(userProjection);
    }

    @EventListener
    public void handleUserNickChanged(UserNickChanged event) {
        //TODO
        //Do somthing with optional :v

        UserProjection projection = repository.findById(event.getUserId().getId()).get();
        projection.setNick(event.getNick());

        repository.save(projection);
    }

}
