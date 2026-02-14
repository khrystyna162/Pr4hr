import java.util.ArrayList;
import java.util.List;


public class DomainEventPublisher {
    private static DomainEventPublisher instance;
    private final List<EventHandler<?>> handlers;

    private DomainEventPublisher() {
        this.handlers = new ArrayList<>();
    }


    public static synchronized DomainEventPublisher getInstance() {
        if (instance == null) {
            instance = new DomainEventPublisher();
        }
        return instance;
    }


    public <T extends DomainEvent> void subscribe(EventHandler<T> handler) {
        handlers.add(handler);
        System.out.println("Обробник зареєстровано: " + handler.getClass().getSimpleName());
    }


    @SuppressWarnings("unchecked")
    public <T extends DomainEvent> void publish(T event) {
        System.out.println("\n>>> Публікація події: " + event.getEventType());
        System.out.println(">>> Час: " + event.getOccurredOn());
        System.out.println(">>> Деталі: " + event);
        System.out.println();

        for (EventHandler<?> handler : handlers) {
            if (handler.canHandle(event)) {
                ((EventHandler<T>) handler).handle(event);
            }
        }
    }


    public void reset() {
        handlers.clear();
        instance = null;
    }


    public int getHandlersCount() {
        return handlers.size();
    }
}