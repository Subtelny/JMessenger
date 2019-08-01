package pl.janda.jmessenger.infrastructure.cqrs;

import pl.janda.jmessenger.infrastructure.cqrs.command.Command;
import pl.janda.jmessenger.infrastructure.cqrs.query.Query;

public interface Bus {

    <R,C extends Command<R>> R executeCommand(C command);

    <R,Q extends Query<R>> R executeQuery(Q query);

}
