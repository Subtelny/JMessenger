package pl.janda.jmessenger.application.commands.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.interfaces.commands.ChangeUserNickCommand;

@Component
@Transactional
@RequiredArgsConstructor
public class ChangeNickUserHandler implements CommandHandler<ChangeNickUserResult, ChangeUserNickCommand> {

    private final Users users;

    @Override
    public ChangeNickUserResult handle(ChangeUserNickCommand command) {
        System.out.println("handled " + command.getNick());

        User user = users.withId(new UserId(command.getUserId()));
        user.changeNick(command.getNick());
        users.save(user);

        return new ChangeNickUserResult(command.getUserId());
    }

}
