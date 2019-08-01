package pl.janda.jmessenger.interfaces.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.janda.jmessenger.application.commands.user.CreateUserResult;
import pl.janda.jmessenger.infrastructure.cqrs.command.Command;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserCommand implements Command<CreateUserResult> {

    private String email;
    private String nick;
    private String password;

}
