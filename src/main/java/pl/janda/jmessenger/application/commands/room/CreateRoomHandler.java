package pl.janda.jmessenger.application.commands.room;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.application.commands.user.CreateUserResult;
import pl.janda.jmessenger.domain.model.room.Messages;
import pl.janda.jmessenger.domain.model.room.Room;
import pl.janda.jmessenger.domain.model.room.RoomId;
import pl.janda.jmessenger.domain.model.room.Rooms;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.interfaces.commands.CreateRoomCommand;
import pl.janda.jmessenger.interfaces.commands.CreateUserCommand;

import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class CreateRoomHandler implements CommandHandler<CreateRoomResult, CreateRoomCommand> {

    private final Rooms rooms;

    @Override
    public CreateRoomResult handle(CreateRoomCommand command) {
        RoomId roomId = new RoomId(UUID.randomUUID().toString());
        Room room = new Room(roomId, command.getDescription());
        rooms.save(room);

        return new CreateRoomResult(roomId.getId());
    }

}
