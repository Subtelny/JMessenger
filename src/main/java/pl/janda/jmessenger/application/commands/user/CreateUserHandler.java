package pl.janda.jmessenger.application.commands.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.janda.jmessenger.domain.model.user.User;
import pl.janda.jmessenger.domain.model.user.UserId;
import pl.janda.jmessenger.domain.model.user.Users;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.interfaces.commands.CreateUserCommand;

@Component
@Transactional
@RequiredArgsConstructor
public class CreateUserHandler implements CommandHandler<CreateUserResult, CreateUserCommand> {

    private final Users users;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResult handle(CreateUserCommand command) {
        System.out.println("handled " + command.getEmail());
        String encodedPassword = passwordEncoder.encode(command.getPassword());

        UserId userId = new UserId(command.getEmail());
        User user = new User(userId, command.getEmail(), command.getNick(), encodedPassword);
        users.save(user);

        return new CreateUserResult(userId.getId());
    }

}
