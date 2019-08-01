package pl.janda.jmessenger.application.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.domain.model.room.RoomCreated;
import pl.janda.jmessenger.domain.model.room.RoomDescriptionChanged;
import pl.janda.jmessenger.infrastructure.persistence.repository.RoomProjectionRepository;

@Component
@RequiredArgsConstructor
public class RoomProjectionEventListener {

    private final RoomProjectionRepository repository;

    @EventListener
    public void handleUserCreated(RoomCreated event) {
        RoomProjection projection = new RoomProjection(
                event.getId().getId(),
                event.getDescription());

        repository.save(projection);
    }

    @EventListener
    public void handleUserNickChanged(RoomDescriptionChanged event) {
        //TODO
        //Do somthing with optional :v

        RoomProjection projection = repository.findById(event.getId().getId()).get();
        projection.setDescription(event.getDescription());

        repository.save(projection);
    }

}
