package pl.janda.jmessenger.application.commands.room;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.application.commands.user.ChangeNickUserResult;
import pl.janda.jmessenger.domain.model.room.Room;
import pl.janda.jmessenger.domain.model.room.RoomId;
import pl.janda.jmessenger.domain.model.room.Rooms;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.interfaces.commands.ChangeRoomDescriptionCommand;
import pl.janda.jmessenger.interfaces.commands.ChangeUserNickCommand;

@Component
@Transactional
@RequiredArgsConstructor
public class ChangeRoomDescriptionHandler implements CommandHandler<ChangeRoomDescriptionResult, ChangeRoomDescriptionCommand> {

    private final Rooms rooms;

    @Override
    public ChangeRoomDescriptionResult handle(ChangeRoomDescriptionCommand command) {
        Room room = rooms.withId(new RoomId(command.getRoomId()));
        Validate.notNull(room, "Room not found");

        room.changeDescription(command.getDescription());
        rooms.save(room);

        return new ChangeRoomDescriptionResult(command.getRoomId());
    }

}
