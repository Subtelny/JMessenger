package pl.janda.jmessenger.application.commands.room;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.domain.model.room.*;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.interfaces.commands.ChangeMessageTextCommand;
import pl.janda.jmessenger.interfaces.commands.ChangeRoomDescriptionCommand;

@Component
@Transactional
@RequiredArgsConstructor
public class ChangeMessageTextHandler implements CommandHandler<ChangeMessageTextResult, ChangeMessageTextCommand> {

    private final Messages messages;

    @Override
    public ChangeMessageTextResult handle(ChangeMessageTextCommand command) {
        Message message = messages.withId(new MessageId(command.getMessageId()));
        Validate.notNull(message, "Room not found");

        message.changeText(command.getText());
        messages.save(message);

        return new ChangeMessageTextResult(command.getMessageId());
    }

}
