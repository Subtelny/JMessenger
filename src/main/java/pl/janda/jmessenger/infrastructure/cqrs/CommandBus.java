package pl.janda.jmessenger.infrastructure.cqrs;

import pl.janda.jmessenger.infrastructure.cqrs.command.Command;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryHandler;

public class CommandBus implements Bus {

    private final HandlerRegistry handlerRegistry;

    public CommandBus(HandlerRegistry handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public <R, C extends Command<R>> R executeCommand(C command) {
        CommandHandler<R, C> commandHandler = (CommandHandler<R, C>) handlerRegistry.getCommandHandler(command.getClass());
        return commandHandler.handle(command);
    }

    @Override
    public <R, Q extends Query<R>> R executeQuery(Q query) {
        QueryHandler<R, Q> commandHandler = (QueryHandler<R, Q>) handlerRegistry.getQueryHandler(query.getClass());
        return commandHandler.handle(query);
    }
}
