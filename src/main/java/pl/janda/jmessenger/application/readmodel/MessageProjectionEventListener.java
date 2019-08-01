package pl.janda.jmessenger.application.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.janda.jmessenger.domain.model.room.MessagedToRoom;
import pl.janda.jmessenger.domain.model.room.MessageTextChanged;
import pl.janda.jmessenger.infrastructure.persistence.repository.MessageProjectionRepository;

@Component
@RequiredArgsConstructor
public class MessageProjectionEventListener {

    private final MessageProjectionRepository repository;

    @EventListener
    public void handleUserCreated(MessagedToRoom event) {
        MessageProjection messageProjection = new MessageProjection(
                event.getMessageId().getId(),
                event.getRoomId().getId(),
                event.getUserId().getId(),
                event.getText(),
                event.getCreatedDate());

        repository.save(messageProjection);
    }

    @EventListener
    public void handleUserNickChanged(MessageTextChanged event) {
        //TODO
        //Do somthing with optional :v

        MessageProjection projection = repository.findById(event.getId().getId()).get();
        projection.setText(event.getText());

        repository.save(projection);
    }

}
