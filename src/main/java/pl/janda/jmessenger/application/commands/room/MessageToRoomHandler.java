package pl.janda.jmessenger.application.commands.room;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.domain.model.room.*;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.interfaces.commands.CreateRoomCommand;
import pl.janda.jmessenger.interfaces.commands.MessageToRoomCommand;

import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class MessageToRoomHandler implements CommandHandler<MessageToRoomResult, MessageToRoomCommand> {

    private final Messages messages;
    private final Users users;
    private final Rooms rooms;

    @Override
    public MessageToRoomResult handle(MessageToRoomCommand command) {
        User user = users.withId(new UserId(command.getUserId()));
        Room room = rooms.withId(new RoomId(command.getRoomId()));

        Validate.notNull(user, "User not found");
        Validate.notNull(room, "Room not found");

        MessageId messageId = new MessageId(UUID.randomUUID().toString());
        UserId userId = user.getUserId();
        RoomId roomId = room.getRoomId();

        Message message = new Message(messageId, roomId, userId, command.getText());
        messages.save(message);

        return new MessageToRoomResult(roomId.getId());
    }

}
