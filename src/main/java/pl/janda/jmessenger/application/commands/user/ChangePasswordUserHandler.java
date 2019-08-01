package pl.janda.jmessenger.application.commands.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.interfaces.commands.ChangeUserPasswordCommand;

@Component
@Transactional
@RequiredArgsConstructor
public class ChangePasswordUserHandler implements CommandHandler<ChangePasswordUserResult, ChangeUserPasswordCommand> {

    private final Users users;
    private final PasswordEncoder encoder;

    @Override
    public ChangePasswordUserResult handle(ChangeUserPasswordCommand command) {
        System.out.println("handled " + command.getPassword());

        User user = users.withId(new UserId(command.getEmail()));
        user.changePassword(encoder.encode(command.getPassword()));
        users.save(user);

        return new ChangePasswordUserResult(command.getEmail());
    }

}
