package pl.janda.jmessenger.infrastructure.cqrs.query;

public interface QueryHandler<R, C extends Query<R>> {

    R handle(C query);

}
