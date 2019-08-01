package pl.janda.jmessenger.interfaces.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.janda.jmessenger.application.commands.room.MessageToRoomResult;
import pl.janda.jmessenger.application.commands.user.ChangePasswordUserResult;
import pl.janda.jmessenger.infrastructure.cqrs.command.Command;

@Getter
@Setter
@AllArgsConstructor
public class MessageToRoomCommand implements Command<MessageToRoomResult> {

    private String roomId;
    private String userId;
    private String text;

}
