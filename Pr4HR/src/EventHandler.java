public interface EventHandler<T extends DomainEvent> {

    void handle(T event);


    boolean canHandle(DomainEvent event);
}
