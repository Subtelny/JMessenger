package pl.janda.jmessenger.infrastructure.cqrs.command;

public interface CommandHandler<R, C extends Command> {

    R handle(C command);

}
