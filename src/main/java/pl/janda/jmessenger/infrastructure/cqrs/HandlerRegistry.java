package pl.janda.jmessenger.infrastructure.cqrs;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import pl.janda.jmessenger.infrastructure.cqrs.command.Command;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandHandler;
import pl.janda.jmessenger.infrastructure.cqrs.command.CommandProvider;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryHandler;
import pl.janda.jmessenger.infrastructure.cqrs.query.QueryProvider;

import java.util.HashMap;
import java.util.Map;

public class HandlerRegistry {

    private Map<Class<? extends Command>, CommandProvider> commandProviderMap = new HashMap<>();
    private Map<Class<? extends Query>, QueryProvider> queryProviderMap = new HashMap<>();

    public HandlerRegistry(ApplicationContext applicationContext) {
        for (String name : applicationContext.getBeanNamesForType(CommandHandler.class)) {
            registerCommand(applicationContext, name);
        }
        for (String name : applicationContext.getBeanNamesForType(QueryHandler.class)) {
            registerQuery(applicationContext, name);
        }
    }

    private void registerCommand(ApplicationContext applicationContext, String name) {
        Class<CommandHandler<?,?>> handlerClass = (Class<CommandHandler<?,?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
        Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
        commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
    }

    private void registerQuery(ApplicationContext applicationContext, String name) {
        Class<QueryHandler<?,?>> handlerClass = (Class<QueryHandler<?,?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
        Class<? extends Query> commandType = (Class<? extends Query>) generics[1];
        queryProviderMap.put(commandType, new QueryProvider(applicationContext, handlerClass));
    }

    <R, C extends Command<R>> CommandHandler<R, C> getCommandHandler(Class<C> commandClass) {
        return commandProviderMap.get(commandClass).get();
    }

    <R, C extends Query<R>> QueryHandler<R,C> getQueryHandler(Class<C> commandClass) {
        return queryProviderMap.get(commandClass).get();
    }

}
