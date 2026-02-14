import java.time.format.DateTimeFormatter;


public class CheckInLoggingHandler implements EventHandler<CheckInCompletedEvent> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void handle(CheckInCompletedEvent event) {
        System.out.println("=== CHECK-IN LOGGING HANDLER ===");
        System.out.println("Логування реєстрації пасажира...");
        System.out.println("ID події: " + event.getEventId());
        System.out.println("Пасажир: " + event.getPassengerId());
        System.out.println("Рейс: " + event.getFlightNumber());
        System.out.println("Місце: " + event.getSeatNumber());
        System.out.println("Час реєстрації: " + event.getCheckInTime().format(formatter));
        System.out.println("Запис додано до системного журналу.");
        System.out.println("================================\n");
    }

    @Override
    public boolean canHandle(DomainEvent event) {
        return event instanceof CheckInCompletedEvent;
    }
}