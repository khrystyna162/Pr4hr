public class EmailNotificationHandler implements EventHandler<BoardingPassGeneratedEvent> {

    @Override
    public void handle(BoardingPassGeneratedEvent event) {
        System.out.println("=== EMAIL NOTIFICATION HANDLER ===");
        System.out.println("Відправка посадкового талона на email...");
        System.out.println("Пасажир: " + event.getPassengerId());
        System.out.println("Рейс: " + event.getFlightNumber());
        System.out.println("Місце: " + event.getSeatNumber());
        System.out.println("Номер посадкового талона: " + event.getBoardingPassNumber());
        System.out.println("Вихід: " + event.getGate());
        System.out.println("Час посадки: " + event.getBoardingTime());
        System.out.println("Email успішно відправлено!");
        System.out.println("==================================\n");
    }

    @Override
    public boolean canHandle(DomainEvent event) {
        return event instanceof BoardingPassGeneratedEvent;
    }
}