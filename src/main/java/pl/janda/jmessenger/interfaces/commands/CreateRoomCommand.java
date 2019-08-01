package pl.janda.jmessenger.interfaces.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.janda.jmessenger.application.commands.room.CreateRoomResult;
import pl.janda.jmessenger.application.commands.user.CreateUserResult;
import pl.janda.jmessenger.infrastructure.cqrs.command.Command;

@Getter
@Setter
@AllArgsConstructor
public class CreateRoomCommand implements Command<CreateRoomResult> {

    private String description;

}
