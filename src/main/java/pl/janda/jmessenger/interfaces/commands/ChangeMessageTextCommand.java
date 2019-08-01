package pl.janda.jmessenger.interfaces.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.janda.jmessenger.application.commands.room.ChangeMessageTextResult;
import pl.janda.jmessenger.application.commands.user.ChangeNickUserResult;
import pl.janda.jmessenger.infrastructure.cqrs.command.Command;

@Getter
@Setter
@AllArgsConstructor
public class ChangeMessageTextCommand implements Command<ChangeMessageTextResult> {

    private String messageId;
    private String text;

}
