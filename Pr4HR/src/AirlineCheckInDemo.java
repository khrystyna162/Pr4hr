import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AirlineCheckInDemo {

    public static void main(String[] args) {
        printHeader("СИСТЕМА РЕЄСТРАЦІЇ НА РЕЙС - ДОМЕННІ ПОДІЇ");


        DomainEventPublisher publisher = DomainEventPublisher.getInstance();


        printHeader("КРОК 1: Реєстрація обробників подій");
        publisher.subscribe(new CheckInLoggingHandler());
        publisher.subscribe(new EmailNotificationHandler());

        System.out.println("Всього зареєстровано обробників: " + publisher.getHandlersCount() + "\n");


        printHeader("КРОК 2: Створення рейсу");
        LocalDateTime departureTime = LocalDateTime.now().plusHours(3);
        Flight flight = new Flight(
                "UA-777",
                "Київ (KBP)",
                "Париж (CDG)",
                departureTime,
                "A12"
        );
        System.out.println("Створено рейс: " + flight);
        System.out.println("Час вильоту: " + departureTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println();


        printHeader("КРОК 3: Створення пасажирів");
        Passenger passenger1 = new Passenger(
                "P001",
                "Іван",
                "Шевченко",
                "ivan.shevchenko@example.com",
                "AA1234567"
        );

        Passenger passenger2 = new Passenger(
                "P002",
                "Марія",
                "Коваленко",
                "maria.kovalenko@example.com",
                "BB7654321"
        );

        System.out.println("Пасажир 1: " + passenger1);
        System.out.println("Пасажир 2: " + passenger2);
        System.out.println();

        
        printHeader("КРОК 4: Реєстрація пасажирів на рейс");

        System.out.println("Реєструємо пасажира 1...\n");
        flight.checkInPassenger(passenger1, "12A");

        waitBetweenOperations();

        System.out.println("Реєструємо пасажира 2...\n");
        flight.checkInPassenger(passenger2, "15C");


        printHeader("ПІДСУМОК");
        System.out.println("Рейс: " + flight.getFlightNumber());
        System.out.println("Напрямок: " + flight.getDepartureCity() + " → " + flight.getArrivalCity());
        System.out.println("Зареєстровано пасажирів: " + flight.getCheckedInPassengersCount());
        System.out.println("\n✅ Всі доменні події успішно оброблені!");
        System.out.println("✅ Посадкові талони відправлені на email пасажирів");
        System.out.println("✅ Реєстрації занесені до системного журналу");

        printFooter();
    }

    private static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  " + title);
        System.out.println("=".repeat(70) + "\n");
    }

    private static void printFooter() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  ДЕМОНСТРАЦІЯ ЗАВЕРШЕНА");
        System.out.println("=".repeat(70));
    }

    private static void waitBetweenOperations() {
        try {
            Thread.sleep(500); // пауза для наочності
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}